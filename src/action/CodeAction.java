package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Getter;
import lombok.Setter;
import model.CodeModule;
import model.PageBean;
import model.Repertory;
import model.User;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import service.CodeService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CodeAction extends ActionSupport implements ModelDriven<CodeModule> {

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "codeModule")
    private CodeModule codeModule;

    @Setter
    @Getter
    private Repertory repertory;

    @Override
    public CodeModule getModel() {
        return codeModule;
    }

    //跳转到创建仓库页面
    public String Creatrep() {

        return "Creatrep";
    }

    //创建代码仓库
    public String Saverep() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //设置时间
        repertory.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //设置用户的id
        repertory.setUser_id(user.getId());
        //设置文件数量
        repertory.setFile_num(0);
        System.out.println(repertory);
        //保存到数据库
        codeService.Saverep(repertory);
        return "Saverep";
    }

    //进入创建代码模块界面
    public String CreatCode() {
        return "CreatCode";
    }

    //新建codeModule
    //接受上传的文件
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传文件，与form表单input发送的name名一样

    public String SaveCodeModule() throws IOException {

        User user = (User) ActionContext.getContext().getSession().get("user");

        //处理上传的文件
        if (upload != null) {
            //获取文件扩展名
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            String boforeName = uploadFileName.substring(0, index);
            //生成文件名==文件名+uuid
            String uuid = UUID.randomUUID().toString();
            //将生成的uuid中的"-"去掉，并拼接扩展名
            String fileName = boforeName + "(" + uuid.replace("-", "") + ")" + exName;

            //新建服务器接受文件的目录
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //路径转文件
            File file = new File(realPath);
            //如果文件不存在，新建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            //拼接新文件路径
            File newFile = new File(realPath + "/" + fileName);
            //把临时文件copy过来
            FileUtil.copyFile(upload, newFile);

            //设置参数
            codeModule.setUser_id(user.getId());
            codeModule.setCode_file(fileName);
            codeModule.setPublic_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            //代码模块保存到数据库
            codeService.SaveCodeModule(codeModule);

        }
        return "SaveCodeModule";
    }

    @Setter
    private Integer currentPage;

    //个人代码模块
    public String myModule() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //查询个人所有代码模块，封装成PageBean
        PageBean<CodeModule> codeModulePageBean = codeService.myModule(user.getId(), currentPage);
        //PageBean存到Session中
        ActionContext.getContext().getSession().put("pageBean", codeModulePageBean);
        return "myModule";
    }

    @Setter
    private Integer uid;

    //别人代码模块列表
    public String otherModule() {
        //查询别人所有代码模块，封装成PageBean
        PageBean<CodeModule> codeModulePageBean = codeService.othModule(uid, currentPage);
        //id查询别人信息
        User userById = codeService.findUserById(uid);
        //PageBean存到Session中
        ActionContext.getContext().getSession().put("oth_pageBean", codeModulePageBean);
        ActionContext.getContext().getSession().put("userById", userById);
        return "otherModule";
    }

    //删除代码模块
    public String deleteCode() {
        //根据id查询出代码模块，删除文件
        CodeModule findCodeById = codeService.FindCodeById(codeModule.getId());
        String code_file = findCodeById.getCode_file();
        if (code_file != null) {
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //拿到文件夹
            File file = new File(realPath);
            //遍历文件夹下所有文件
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                if (code_file.equals(listFile.getName())) {
                    listFile.delete();
                }
            }
        }
        //删除代码模块
        codeService.deleteCode(codeModule);

        return "deleteCode";
    }

    //修改代码模块
    public String ModifyCode() {
        CodeModule findCodeById = codeService.FindCodeById(codeModule.getId());
        ActionContext.getContext().getSession().put("codefile", findCodeById.getCode_file());
        //还原文件名
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //存到session中
        ActionContext.getContext().getSession().put("codeModule", findCodeById);
        return "ModifyCode";
    }

    //提交修改代码模块
    public String ModifyCodeModule() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        String codefile = (String) ActionContext.getContext().getSession().get("codefile");
        //处理上传的文件
        if (upload != null) {

            //删除原文件
            if (codefile != null) {
                String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
                //拿到文件夹
                File file = new File(realPath);
                //遍历文件夹下所有文件
                File[] listFiles = file.listFiles();
                for (File listFile : listFiles) {
                    if (codefile.equals(listFile.getName())) {
                        listFile.delete();
                    }
                }
            }

            //获取文件扩展名
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            String boforeName = uploadFileName.substring(0, index);
            //生成文件名==文件名+uuid
            String uuid = UUID.randomUUID().toString();
            //将生成的uuid中的"-"去掉，并拼接扩展名
            String fileName = boforeName + "(" + uuid.replace("-", "") + ")" + exName;

            //新建服务器接受文件的目录
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //路径转文件
            File file = new File(realPath);
            //如果文件不存在，新建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            //拼接新文件路径
            File newFile = new File(realPath + "/" + fileName);
            //把临时文件copy过来
            FileUtil.copyFile(upload, newFile);

            codeModule.setCode_file(fileName);
        }
        //设置参数
        codeModule.setUser_id(user.getId());
        if (upload == null) {
            codeModule.setCode_file(codefile);
        }
        codeModule.setPublic_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //代码模块保存到数据库
        codeService.updateCodeModule(codeModule);
        return "ModifyCodeModule";
    }

    //查看个人代码模块
    public String ViewCode() throws Exception {
        CodeModule findCodeById = codeService.FindCodeById(this.codeModule.getId());
        //加载文件内容
        String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
        String path = realPath + "/" + findCodeById.getCode_file();
        String context = codeService.ReadFile(path);
        //还原文件名
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //存到session中
        ActionContext.getContext().getSession().put("ViewCode", findCodeById);
        ActionContext.getContext().getSession().put("context", context);
        return "ViewCode";
    }

    //进入市场界面
    @Setter
    private String keyname;
    @Setter
    private String type;
    @Setter
    private String Porder;

    public String market() {
        //定义离线查询语句
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);

        if (keyname != null) {
            //添加模糊查询条件
            detachedCriteria.add(Restrictions.like("code_name", "%" + keyname + "%"));
        }

        if (type != null) {
            //添加查询条件
            detachedCriteria.add(Restrictions.eq("language", type));
        }

        if ("up".equals(Porder)) {
            //按价格升序
            detachedCriteria.addOrder(Order.asc("price"));
        }

        if ("down".equals(Porder)) {
            //按价格升序
            detachedCriteria.addOrder(Order.desc("price"));
        }

        if (Porder == null) {
            detachedCriteria.addOrder(Order.desc("public_time"));
        }

        //查询出所有人的代码模块
        PageBean<CodeModule> modulePageBean = codeService.FindAllCode(currentPage, detachedCriteria);
        //PageBean存到Session中
        ActionContext.getContext().getSession().put("modulePageBean", modulePageBean);
        return "market";
    }

    //代码详情页
    public String CodeDetail() throws Exception {
        CodeModule findCodeById = codeService.FindCodeById(this.codeModule.getId());
        //加载文件内容
        String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
        String path = realPath + "/" + findCodeById.getCode_file();
        String context = codeService.ReadFile(path);
        //还原文件名
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //查询出代码模块的归属者
        User user = codeService.findUserById(findCodeById.getUser_id());
        //存到session中
        ActionContext.getContext().getSession().put("CodeDetail", findCodeById);
        ActionContext.getContext().getSession().put("context", context);
        ActionContext.getContext().getSession().put("codeuser", user);
        return "CodeDetail";
    }

    @Setter
    private String status;

    //进入project页面
    public String project() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        if ("private".equals(status)) {
            //分页查询个人project(private)
            PageBean<Repertory> repertories = codeService.project2(user.getId(), currentPage);
            ActionContext.getContext().getSession().put("repertories", repertories);
        }else {
            //分页查询个人project(public)
            PageBean<Repertory> repertories = codeService.project(user.getId(), currentPage);
            ActionContext.getContext().getSession().put("repertories", repertories);
        }
        return "project";
    }
}
