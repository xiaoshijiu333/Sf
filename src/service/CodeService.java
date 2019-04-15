package service;

import dao.CodeDao;
import model.*;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Transactional
public class CodeService {

    @Resource(name = "codeDao")
    private CodeDao codeDao;

    @Resource(name = "pageBean")
    private PageBean pageBean;

    public void Saverep(Repertory repertory) {
        codeDao.Saverep(repertory);
    }

    //新建代码模块
    public void SaveCodeModule(CodeModule codeModule) {
        codeDao.SaveCodeModule(codeModule);
    }

    public PageBean<CodeModule> myModule(Integer id, Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.GetModuleById(id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<CodeModule> codeModules = codeDao.GetMyModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //根据id查询代码模块
    public CodeModule FindCodeById(Integer id) {
        return codeDao.FindCodeById(id);
    }

    //删除代码模块
    public void deleteCode(CodeModule codeModule) {
        codeDao.deleteCode(codeModule);
    }

    //修改代码模块
    public void updateCodeModule(CodeModule codeModule) {
        codeDao.updateCodeModule(codeModule);
    }

    //读取文件内容
    public String ReadFile(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            result = result + line + "<br>";
        }
        br.close();
        isr.close();
        fis.close();
        return result;
    }

    public PageBean<CodeModule> FindAllCode(Integer currentPage, DetachedCriteria detachedCriteria) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.GetModuleCount(detachedCriteria);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<CodeModule> codeModules = codeDao.GetAllModule(index, pageBean.getPageSize(), detachedCriteria);
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //id查询用户
    public User findUserById(Integer user_id) {
        return codeDao.findUserById(user_id);
    }

    //根据id查询购买次数
    public Integer OrderCount(Integer user_id) {
        return codeDao.OrderCount(user_id);
    }

    //保存购买记录
    public void SaveOrder(OrderModule orderModule) {
        codeDao.SaveOrder(orderModule);
    }

    //查询我购买的代码模块
    public PageBean<OrderModule> BuyModule(Integer id, Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.OrderCount(id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<OrderModule> codeModules = codeDao.GetBuyModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //查询是否已经购买
    public String IsBuy(Integer user_id, Integer id) {
        Boolean isBuy = codeDao.IsBuy(user_id, id);
        if (isBuy) {
            return "yes";
        } else {
            return "no";
        }
    }

    //查询个人收藏代码模块
    public PageBean<LikeCode> likeCode(Integer id, Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.LikeCount(id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<LikeCode> likeCodes = codeDao.GetLikeModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(likeCodes);
        return pageBean;
    }

    //添加收藏代码模块
    public void AddLikeCode(LikeCode likeCode) {
        codeDao.AddLikeCode(likeCode);
    }

    //查询是否已经收藏
    public String IsLike(Integer user_id, Integer id) {
        Boolean Islike = codeDao.IsLike(user_id, id);
        if (Islike) {
            return "yes";
        } else {
            return "no";
        }
    }

    //取消收藏
    public void DeleteLikeCode(LikeCode likeCode) {
        codeDao.DeleteLikeCode(likeCode);
    }


    //查询指定的收藏代码
    public LikeCode FindLikeCode(Integer user_id, Integer id) {
        return codeDao.FindLikeCode(user_id, id);
    }

    public PageBean<CodeModule> othModule(Integer user_id, Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.GetModuleById(user_id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<CodeModule> codeModules = codeDao.GetMyModule(user_id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //分页查询个人project(public)
    public PageBean<Repertory> project(Integer id,Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.projectCount(id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<Repertory> repertories = codeDao.Getprojects(id, index, pageBean.getPageSize());
        pageBean.setPageList(repertories);
        return pageBean;
    }

    //分页查询个人project(private)
    public PageBean<Repertory> project2(Integer id,Integer currentPage) {
        //设置当前页(已经设置了如果是null，就是1)
        pageBean.setCurrentPage(currentPage);
        //一页显示几条数据
        pageBean.setPageSize(5);
        //从数据库查询的角标
        Integer index = pageBean.getIndex();
        //总记录数
        Integer count = codeDao.projectCount2(id);
        pageBean.setTotalCount(count);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //当前页数据
        List<Repertory> repertories = codeDao.Getprojects2(id, index, pageBean.getPageSize());
        pageBean.setPageList(repertories);
        return pageBean;
    }

    //查询自己的最新三个代码模块
    public List<CodeModule> MyThreeModule(Integer user_id,DetachedCriteria detachedCriteria){
        return codeDao.MyThreeModule(user_id,detachedCriteria);
    }

    //更新代码仓库
    public void UploadRep(Repertory repertory) {
        codeDao.updateRep(repertory);
    }

    //根据id查询代码仓库
    public Repertory queryProById(Integer pid) {
        return codeDao.queryProById(pid);
    }
}
