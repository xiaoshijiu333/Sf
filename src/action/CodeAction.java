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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //��ת�������ֿ�ҳ��
    public String Creatrep() {

        return "Creatrep";
    }

    //��������ֿ��һ��
    public String CreateProject() {
        User user = (User) ActionContext.getContext().getSession().get("user");

        //����ʱ��
        repertory.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //�����û���id
        repertory.setUser_id(user.getId());
        //�����ļ�����
        repertory.setFile_num(0);
        //���浽���ݿ�
        codeService.Saverep(repertory);
        ActionContext.getContext().getSession().put("repertory", repertory);

        return "CreateProject";
    }

    //��������ֿ�ڶ���
    public String CreateProject2() {

        //�������߲�ѯ���
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        User user = (User) ActionContext.getContext().getSession().get("user");
        if (type != null) {
            //��Ӳ�ѯ����
            detachedCriteria.add(Restrictions.eq("language", type));
        }

        //��һ�¸��û������µ���������ģ��
        List<CodeModule> threeModule = codeService.MyThreeModule(user.getId(), detachedCriteria);

        ActionContext.getContext().getSession().put("threeModule", threeModule);
        return "CreateProject2";
    }

    @Setter
    private File[] uploadfile;
    @Setter
    private String[] uploadfileFileName;

    //�����ϴ����ļ���
    public String uploadFiles() throws IOException {
        if (uploadfile != null) {
            Repertory repertory = (Repertory) ActionContext.getContext().getSession().get("repertory");
            //�½������������ļ���Ŀ¼(���ݲֿ�id)
            String realPath = ServletActionContext.getServletContext().getRealPath("/Repertoryfiles/" + repertory.getId());
            //·��ת�ļ�
            File file = new File(realPath);
            //����ļ������ڣ��½��ļ���
            if (!file.exists()) {
                file.mkdirs();
            }
            for (int i = 0; i < uploadfileFileName.length; i++) {
                //ƴ�����ļ�·��
                File newFile = new File(realPath + "/" + uploadfileFileName[i]);
                //����ʱ�ļ�copy����
                FileUtil.copyFile(uploadfile[i], newFile);
            }
        }
        return null;
    }

    //����ֿ�����Ĵ����ļ�����
    @Setter
    private String[] codefiles;

    @Setter
    private String infos;

    //���գ��ڶ���������ֿ�
    public String Saverep() throws IOException {

        Repertory repertory = (Repertory) ActionContext.getContext().getSession().get("repertory");

        //���¶���info
        repertory.setInfo(infos);

        //�����ļ�����
        String realPath = ServletActionContext.getServletContext().getRealPath("/Repertoryfiles/" + repertory.getId());
        String realPath2 = ServletActionContext.getServletContext().getRealPath("/codefile");
        File file = new File(realPath);
        //����ļ������ڣ��½��ļ���
        if (!file.exists()) {
            file.mkdirs();
        }

        //�����д����ļ��ŵ�������һ�ݣ��òֿ��ļ�����
        if (codefiles != null) {
            for (String codefile : codefiles) {
                //�����ļ�
                File newFile = new File(realPath2 + "/" + codefile);
                File newFile2 = new File(realPath + "/" + codefile);
                FileUtil.copyFile(newFile, newFile2);
            }
        }

        //�õ��ļ����������ļ�
        File[] listFiles = file.listFiles();
        repertory.setFile_num(listFiles.length);

        //�޸�ʱ��
        repertory.setModify_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //������µ����ݿ�
        codeService.UploadRep(repertory);

        return null;
    }

    //����ֿ�id
    @Setter
    private Integer pid;

    //��ѯ��������ֿ�
    public String proDetail() {
        //����id��ѯ����ֿ�
        Repertory repertoryById = codeService.queryProById(pid);
        //info��id�浽���У�idΪ�˺��ڼ����ļ����ݣ�
        ActionContext.getContext().getSession().put("repertoryById", repertoryById);
        //��ȡ�ļ�
        String realPath = ServletActionContext.getServletContext().getRealPath("/Repertoryfiles/" + pid);
        File file = new File(realPath);
        //�����ļ����������ļ�
        File[] listFiles = file.listFiles();
        ArrayList<String> filenames = new ArrayList<>();
        for (File listFile : listFiles) {
            filenames.add(listFile.getName());
        }
        ActionContext.getContext().getSession().put("filenames", filenames);
        return "proDetail";
    }

    @Setter
    private String filename;

    //�鿴�ļ�����
    public String fileDetail() throws Exception {
        String realPath = ServletActionContext.getServletContext().getRealPath("/Repertoryfiles/" + pid + "/" + filename);
        //�����ļ�����
        String readFile = codeService.ReadFile(realPath);
        ActionContext.getContext().getSession().put("readFile", readFile);
        return "fileDetail";
    }

    //���봴������ģ�����
    public String CreatCode() {
        return "CreatCode";
    }

    //�½�codeModule
    //�����ϴ����ļ�
    @Setter
    private String uploadFileName; // �ļ�����
    @Setter
    private File upload; // �ϴ��ļ�����form��input���͵�name��һ��

    public String SaveCodeModule() throws IOException {

        User user = (User) ActionContext.getContext().getSession().get("user");

        //�����ϴ����ļ�
        if (upload != null) {
            //��ȡ�ļ���չ��
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            String boforeName = uploadFileName.substring(0, index);
            //�����ļ���==�ļ���+uuid
            String uuid = UUID.randomUUID().toString();
            //�����ɵ�uuid�е�"-"ȥ������ƴ����չ��
            String fileName = boforeName + "(" + uuid.replace("-", "") + ")" + exName;

            //�½������������ļ���Ŀ¼
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //·��ת�ļ�
            File file = new File(realPath);
            //����ļ������ڣ��½��ļ���
            if (!file.exists()) {
                file.mkdirs();
            }
            //ƴ�����ļ�·��
            File newFile = new File(realPath + "/" + fileName);
            //����ʱ�ļ�copy����
            FileUtil.copyFile(upload, newFile);

            //���ò���
            codeModule.setUser_id(user.getId());
            codeModule.setCode_file(fileName);
            codeModule.setPublic_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            //����ģ�鱣�浽���ݿ�
            codeService.SaveCodeModule(codeModule);

        }
        return "SaveCodeModule";
    }

    @Setter
    private Integer currentPage;

    //���˴���ģ��
    public String myModule() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //��ѯ�������д���ģ�飬��װ��PageBean
        PageBean<CodeModule> codeModulePageBean = codeService.myModule(user.getId(), currentPage);
        //PageBean�浽Session��
        ActionContext.getContext().getSession().put("pageBean", codeModulePageBean);
        return "myModule";
    }

    @Setter
    private Integer uid;

    //���˴���ģ���б�
    public String otherModule() {
        //��ѯ�������д���ģ�飬��װ��PageBean
        PageBean<CodeModule> codeModulePageBean = codeService.othModule(uid, currentPage);
        //id��ѯ������Ϣ
        User userById = codeService.findUserById(uid);
        //PageBean�浽Session��
        ActionContext.getContext().getSession().put("oth_pageBean", codeModulePageBean);
        ActionContext.getContext().getSession().put("userById", userById);
        return "otherModule";
    }

    //ɾ������ģ��
    public String deleteCode() {
        //����id��ѯ������ģ�飬ɾ���ļ�
        CodeModule findCodeById = codeService.FindCodeById(codeModule.getId());
        String code_file = findCodeById.getCode_file();
        if (code_file != null) {
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //�õ��ļ���
            File file = new File(realPath);
            //�����ļ����������ļ�
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                if (code_file.equals(listFile.getName())) {
                    listFile.delete();
                }
            }
        }
        //ɾ������ģ��
        codeService.deleteCode(codeModule);

        return "deleteCode";
    }

    //�޸Ĵ���ģ��
    public String ModifyCode() {
        CodeModule findCodeById = codeService.FindCodeById(codeModule.getId());
        ActionContext.getContext().getSession().put("codefile", findCodeById.getCode_file());
        //��ԭ�ļ���
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //�浽session��
        ActionContext.getContext().getSession().put("codeModule", findCodeById);
        return "ModifyCode";
    }

    //�ύ�޸Ĵ���ģ��
    public String ModifyCodeModule() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        String codefile = (String) ActionContext.getContext().getSession().get("codefile");
        //�����ϴ����ļ�
        if (upload != null) {

            //ɾ��ԭ�ļ�
            if (codefile != null) {
                String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
                //�õ��ļ���
                File file = new File(realPath);
                //�����ļ����������ļ�
                File[] listFiles = file.listFiles();
                for (File listFile : listFiles) {
                    if (codefile.equals(listFile.getName())) {
                        listFile.delete();
                    }
                }
            }

            //��ȡ�ļ���չ��
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            String boforeName = uploadFileName.substring(0, index);
            //�����ļ���==�ļ���+uuid
            String uuid = UUID.randomUUID().toString();
            //�����ɵ�uuid�е�"-"ȥ������ƴ����չ��
            String fileName = boforeName + "(" + uuid.replace("-", "") + ")" + exName;

            //�½������������ļ���Ŀ¼
            String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
            //·��ת�ļ�
            File file = new File(realPath);
            //����ļ������ڣ��½��ļ���
            if (!file.exists()) {
                file.mkdirs();
            }
            //ƴ�����ļ�·��
            File newFile = new File(realPath + "/" + fileName);
            //����ʱ�ļ�copy����
            FileUtil.copyFile(upload, newFile);

            codeModule.setCode_file(fileName);
        }
        //���ò���
        codeModule.setUser_id(user.getId());
        if (upload == null) {
            codeModule.setCode_file(codefile);
        }
        codeModule.setPublic_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //����ģ�鱣�浽���ݿ�
        codeService.updateCodeModule(codeModule);
        return "ModifyCodeModule";
    }

    //�鿴���˴���ģ��
    public String ViewCode() throws Exception {
        CodeModule findCodeById = codeService.FindCodeById(this.codeModule.getId());
        //�����ļ�����
        String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
        String path = realPath + "/" + findCodeById.getCode_file();
        String context = codeService.ReadFile(path);
        //��ԭ�ļ���
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //�浽session��
        ActionContext.getContext().getSession().put("ViewCode", findCodeById);
        ActionContext.getContext().getSession().put("context", context);
        return "ViewCode";
    }

    //�����г�����
    @Setter
    private String keyname;
    @Setter
    private String type;
    @Setter
    private String Porder;

    public String market() {
        //�������߲�ѯ���
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);

        if (keyname != null) {
            //���ģ����ѯ����
            detachedCriteria.add(Restrictions.like("code_name", "%" + keyname + "%"));
        }

        if (type != null) {
            //��Ӳ�ѯ����
            detachedCriteria.add(Restrictions.eq("language", type));
        }

        if ("up".equals(Porder)) {
            //���۸�����
            detachedCriteria.addOrder(Order.asc("price"));
        }

        if ("down".equals(Porder)) {
            //���۸�����
            detachedCriteria.addOrder(Order.desc("price"));
        }

        if (Porder == null) {
            detachedCriteria.addOrder(Order.desc("public_time"));
        }

        //��ѯ�������˵Ĵ���ģ��
        PageBean<CodeModule> modulePageBean = codeService.FindAllCode(currentPage, detachedCriteria);
        //PageBean�浽Session��
        ActionContext.getContext().getSession().put("modulePageBean", modulePageBean);
        return "market";
    }

    //��������ҳ
    public String CodeDetail() throws Exception {
        CodeModule findCodeById = codeService.FindCodeById(this.codeModule.getId());
        //�����ļ�����
        String realPath = ServletActionContext.getServletContext().getRealPath("/codefile");
        String path = realPath + "/" + findCodeById.getCode_file();
        String context = codeService.ReadFile(path);
        //��ԭ�ļ���
        int index1 = findCodeById.getCode_file().lastIndexOf(".");
        int index2 = findCodeById.getCode_file().indexOf("(");
        String exName = findCodeById.getCode_file().substring(index1);
        String boforeName = findCodeById.getCode_file().substring(0, index2);
        findCodeById.setCode_file(boforeName + exName);
        //��ѯ������ģ��Ĺ�����
        User user = codeService.findUserById(findCodeById.getUser_id());
        //�浽session��
        ActionContext.getContext().getSession().put("CodeDetail", findCodeById);
        ActionContext.getContext().getSession().put("context", context);
        ActionContext.getContext().getSession().put("codeuser", user);
        return "CodeDetail";
    }

    @Setter
    private String status;

    //����projectҳ��
    public String project() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        if ("private".equals(status)) {
            //��ҳ��ѯ����project(private)
            PageBean<Repertory> repertories = codeService.project2(user.getId(), currentPage);
            ActionContext.getContext().getSession().put("repertories", repertories);
        } else {
            //��ҳ��ѯ����project(public)
            PageBean<Repertory> repertories = codeService.project(user.getId(), currentPage);
            ActionContext.getContext().getSession().put("repertories", repertories);
        }
        return "project";
    }
}
