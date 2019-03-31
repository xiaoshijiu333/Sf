package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Getter;
import lombok.Setter;
import model.Message;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "user")
    private User user1;

    @Resource(name = "message")
    private Message message;

    @Override
    public User getModel() {
        return user1;
    }

    //�����ϴ���ͷ��
    @Setter
    private String uploadFileName; // �ļ�����
    @Setter
    private File upload; // �ϴ��ļ�����form��input���͵�name��һ��

    public String UserPicture() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");

        //�û��޸�ͷ��ʱ��ɾ��ԭ�����ڷ�������ͷ�񣡽�Լ�ڴ棡
        String upicture = user.getHead_image();
        if(upicture!=null){
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            //�õ��ļ���
            File file = new File(realPath);
            //�����ļ����������ļ�
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                if (upicture.equals(listFile.getName())){
                    listFile.delete();
                }
            }
        }

        //�����ϴ����ļ�
        if(upload!=null){
            //��ȡ�ļ���չ��
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            //��������ļ���
            String uuid = UUID.randomUUID().toString();
            //�����ɵ�uuid�е�"-"ȥ������ƴ����չ��
            String fileName = uuid.replace("-", "") + exName;

            //�½������������ļ���Ŀ¼
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            //·��ת�ļ�
            File file = new File(realPath);
            //����ļ������ڣ��½��ļ���
            if(!file.exists()){
                file.mkdirs();
            }
            //ƴ�����ļ�·��
            File newFile = new File(realPath + "/" + fileName);
            //����ʱ�ļ�copy����
            FileUtil.copyFile(upload,newFile);

            user.setHead_image(fileName);

            //�û���Ϣ���µ����ݿ�
            userService.UpdateUser(user);

            //���¶����û�session
            ActionContext.getContext().getSession().put("user", user);

            //���ݷ��ظ�ǰ��
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(fileName);
        }

        return null;
    }

    //�����û���Ϣ
    public String update(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //����ͷ���id
        user1.setHead_image(user.getHead_image());
        user1.setId(user.getId());
        userService.UpdateUser(user1);
        //���¶����û�session
        ActionContext.getContext().getSession().put("user", user1);
        return "update";
    }

    //�˳���¼
    public String loginOut(){
        ActionContext.getContext().getSession().remove("user");
        return "loginOut";
    }

    @Setter
    private Integer to_id;
    @Setter
    private String context;
    //����˽��
    public void SendMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        //��ѯ��Ҫ���͵���
        User toUser=userService.GetUserById(to_id);
        //���ò���
        message.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        message.setFromUser(user);
        message.setToUser(toUser);
        message.setContext(context);
        message.setStatus(0);
        //���浽���ݿ�
        userService.SaveMessage(message);
        //д��ǰ��һ��time
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(message.getTime());
    }
    @Setter
    private Integer from_id;
    //��ѯ���˷����ҵ�δ��˽��
    public void QueryMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        List<Message> messageList=userService.QueryMessage(user.getId());
        if (messageList.size()>0){
            //д��ǰ��δ����Ϣ
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(messageList.get(0).getFromUser().getId());
        }

    }
    //��ѯָ���û����ҷ���˽��
    public void OneMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        List<Message> messages=userService.OneMessage(from_id,user.getId());
        //����Ϣ���Ϊ�Ѷ�
        userService.HadRead(from_id,user.getId());
        if (messages.size()>0){
            //д��ǰ��δ����Ϣ   json��ʽ����
            JsonConfig jsonConfig = new JsonConfig();
            //���˵�user������
            jsonConfig.setExcludes(new String[]{"password"});
            JSONArray jsonObject = JSONArray.fromObject(messages,jsonConfig);

            //json����д�������
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
        }
    }
    //����˽�Ž���
    public String ToMessage(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //��ѯ������֮���ȫ��˽��
        List<Message> messageList=userService.QueryAllMessage(from_id,user.getId());
        //id��ѯ��user
        User codeuser = userService.GetUserById(from_id);
        //����Ϣ���Ϊ�Ѷ�
        userService.HadRead(from_id,user.getId());
        //���浽����
        ActionContext.getContext().getSession().put("messageList",messageList);
        ActionContext.getContext().getSession().put("codeuser",codeuser);
        return "ToMessage";
    }
    //����˽��ͼƬ
    public void Mespic() throws IOException {
        //�����ϴ����ļ�
        if(upload!=null){
            //��ȡ�ļ���չ��
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            //��������ļ���
            String uuid = UUID.randomUUID().toString();
            //�����ɵ�uuid�е�"-"ȥ������ƴ����չ��
            String fileName = uuid.replace("-", "") + exName;

            //�½������������ļ���Ŀ¼
            String realPath = ServletActionContext.getServletContext().getRealPath("/messagepic");
            //·��ת�ļ�
            File file = new File(realPath);
            //����ļ������ڣ��½��ļ���
            if(!file.exists()){
                file.mkdirs();
            }
            //ƴ�����ļ�·��
            File newFile = new File(realPath + "/" + fileName);
            //����ʱ�ļ�copy����
            FileUtil.copyFile(upload,newFile);

            User user =(User) ActionContext.getContext().getSession().get("user");
            //��ѯ��Ҫ���͵���
            User toUser=userService.GetUserById(to_id);
            //���ò���
            message.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            message.setFromUser(user);
            message.setToUser(toUser);
            message.setContext(fileName);
            message.setStatus(0);
            //���浽���ݿ�
            userService.SaveMessage(message);

            //���ݷ��ظ�ǰ��
            //д��ǰ��δ����Ϣ   json��ʽ����
            JsonConfig jsonConfig = new JsonConfig();
            //���˵�user������
            jsonConfig.setExcludes(new String[]{"fromUser","toUser"});
            JSONObject jsonObject = JSONObject.fromObject(message,jsonConfig);
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
        }
    }

    //��ѯ����������Ϣ
    public String threeMessage(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        List<Message> threeMessage=userService.threeMessage(user.getId());
        List<Message> WeekMessage=userService.WeekMessage(user.getId());
        Set<User> weekmes=new HashSet<>();
        Set<User> beforemes=new HashSet<>();
        List<Message> BeforeMessage=userService.BeforeMessage(user.getId());
        for (Message weekmessage : WeekMessage) {
            weekmes.add(weekmessage.getFromUser());
        }
        for (Message beforemessage : BeforeMessage) {
            beforemes.add(beforemessage.getFromUser());
        }
        //���浽����
        ActionContext.getContext().getSession().put("threeMessage",threeMessage);
        ActionContext.getContext().getSession().put("WeekMessage",WeekMessage);
        ActionContext.getContext().getSession().put("BeforeMessage",BeforeMessage);
        ActionContext.getContext().getSession().put("weekmes",weekmes);
        ActionContext.getContext().getSession().put("beforemes",beforemes);
        return "threeMessage";
    }

    //����˽��
    public void search() throws IOException {
        //username��ѯ�û�
        Integer uid=userService.search(user1.getUsername());
        if (uid==null){
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print("err");
        }else{
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(uid);
        }

    }
}
