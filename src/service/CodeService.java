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

    //�½�����ģ��
    public void SaveCodeModule(CodeModule codeModule) {
        codeDao.SaveCodeModule(codeModule);
    }

    public PageBean<CodeModule> myModule(Integer id, Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.GetModuleById(id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<CodeModule> codeModules = codeDao.GetMyModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //����id��ѯ����ģ��
    public CodeModule FindCodeById(Integer id) {
        return codeDao.FindCodeById(id);
    }

    //ɾ������ģ��
    public void deleteCode(CodeModule codeModule) {
        codeDao.deleteCode(codeModule);
    }

    //�޸Ĵ���ģ��
    public void updateCodeModule(CodeModule codeModule) {
        codeDao.updateCodeModule(codeModule);
    }

    //��ȡ�ļ�����
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
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.GetModuleCount(detachedCriteria);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<CodeModule> codeModules = codeDao.GetAllModule(index, pageBean.getPageSize(), detachedCriteria);
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //id��ѯ�û�
    public User findUserById(Integer user_id) {
        return codeDao.findUserById(user_id);
    }

    //����id��ѯ�������
    public Integer OrderCount(Integer user_id) {
        return codeDao.OrderCount(user_id);
    }

    //���湺���¼
    public void SaveOrder(OrderModule orderModule) {
        codeDao.SaveOrder(orderModule);
    }

    //��ѯ�ҹ���Ĵ���ģ��
    public PageBean<OrderModule> BuyModule(Integer id, Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.OrderCount(id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<OrderModule> codeModules = codeDao.GetBuyModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //��ѯ�Ƿ��Ѿ�����
    public String IsBuy(Integer user_id, Integer id) {
        Boolean isBuy = codeDao.IsBuy(user_id, id);
        if (isBuy) {
            return "yes";
        } else {
            return "no";
        }
    }

    //��ѯ�����ղش���ģ��
    public PageBean<LikeCode> likeCode(Integer id, Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.LikeCount(id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<LikeCode> likeCodes = codeDao.GetLikeModule(id, index, pageBean.getPageSize());
        pageBean.setPageList(likeCodes);
        return pageBean;
    }

    //����ղش���ģ��
    public void AddLikeCode(LikeCode likeCode) {
        codeDao.AddLikeCode(likeCode);
    }

    //��ѯ�Ƿ��Ѿ��ղ�
    public String IsLike(Integer user_id, Integer id) {
        Boolean Islike = codeDao.IsLike(user_id, id);
        if (Islike) {
            return "yes";
        } else {
            return "no";
        }
    }

    //ȡ���ղ�
    public void DeleteLikeCode(LikeCode likeCode) {
        codeDao.DeleteLikeCode(likeCode);
    }


    //��ѯָ�����ղش���
    public LikeCode FindLikeCode(Integer user_id, Integer id) {
        return codeDao.FindLikeCode(user_id, id);
    }

    public PageBean<CodeModule> othModule(Integer user_id, Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.GetModuleById(user_id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<CodeModule> codeModules = codeDao.GetMyModule(user_id, index, pageBean.getPageSize());
        pageBean.setPageList(codeModules);
        return pageBean;
    }

    //��ҳ��ѯ����project(public)
    public PageBean<Repertory> project(Integer id,Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.projectCount(id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<Repertory> repertories = codeDao.Getprojects(id, index, pageBean.getPageSize());
        pageBean.setPageList(repertories);
        return pageBean;
    }

    //��ҳ��ѯ����project(private)
    public PageBean<Repertory> project2(Integer id,Integer currentPage) {
        //���õ�ǰҳ(�Ѿ������������null������1)
        pageBean.setCurrentPage(currentPage);
        //һҳ��ʾ��������
        pageBean.setPageSize(5);
        //�����ݿ��ѯ�ĽǱ�
        Integer index = pageBean.getIndex();
        //�ܼ�¼��
        Integer count = codeDao.projectCount2(id);
        pageBean.setTotalCount(count);
        //������ҳ��
        pageBean.setTotalPage(pageBean.getTotalPage());
        //��ǰҳ����
        List<Repertory> repertories = codeDao.Getprojects2(id, index, pageBean.getPageSize());
        pageBean.setPageList(repertories);
        return pageBean;
    }

    //��ѯ�Լ���������������ģ��
    public List<CodeModule> MyThreeModule(Integer user_id,DetachedCriteria detachedCriteria){
        return codeDao.MyThreeModule(user_id,detachedCriteria);
    }

    //���´���ֿ�
    public void UploadRep(Repertory repertory) {
        codeDao.updateRep(repertory);
    }

    //����id��ѯ����ֿ�
    public Repertory queryProById(Integer pid) {
        return codeDao.queryProById(pid);
    }
}
