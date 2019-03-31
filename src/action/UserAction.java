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

    //处理上传的头像
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传文件，与form表单input发送的name名一样

    public String UserPicture() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");

        //用户修改头像时，删除原来存在服务器的头像！节约内存！
        String upicture = user.getHead_image();
        if(upicture!=null){
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            //拿到文件夹
            File file = new File(realPath);
            //遍历文件夹下所有文件
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                if (upicture.equals(listFile.getName())){
                    listFile.delete();
                }
            }
        }

        //处理上传的文件
        if(upload!=null){
            //获取文件扩展名
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            //随机生成文件名
            String uuid = UUID.randomUUID().toString();
            //将生成的uuid中的"-"去掉，并拼接扩展名
            String fileName = uuid.replace("-", "") + exName;

            //新建服务器接受文件的目录
            String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
            //路径转文件
            File file = new File(realPath);
            //如果文件不存在，新建文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File newFile = new File(realPath + "/" + fileName);
            //把临时文件copy过来
            FileUtil.copyFile(upload,newFile);

            user.setHead_image(fileName);

            //用户信息更新到数据库
            userService.UpdateUser(user);

            //重新定义用户session
            ActionContext.getContext().getSession().put("user", user);

            //数据返回给前端
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(fileName);
        }

        return null;
    }

    //更新用户信息
    public String update(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //设置头像和id
        user1.setHead_image(user.getHead_image());
        user1.setId(user.getId());
        userService.UpdateUser(user1);
        //重新定义用户session
        ActionContext.getContext().getSession().put("user", user1);
        return "update";
    }

    //退出登录
    public String loginOut(){
        ActionContext.getContext().getSession().remove("user");
        return "loginOut";
    }

    @Setter
    private Integer to_id;
    @Setter
    private String context;
    //发送私信
    public void SendMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        //查询出要发送的人
        User toUser=userService.GetUserById(to_id);
        //设置参数
        message.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        message.setFromUser(user);
        message.setToUser(toUser);
        message.setContext(context);
        message.setStatus(0);
        //保存到数据库
        userService.SaveMessage(message);
        //写给前端一个time
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(message.getTime());
    }
    @Setter
    private Integer from_id;
    //查询别人发给我的未读私信
    public void QueryMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        List<Message> messageList=userService.QueryMessage(user.getId());
        if (messageList.size()>0){
            //写给前端未读信息
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(messageList.get(0).getFromUser().getId());
        }

    }
    //查询指定用户给我发的私信
    public void OneMessage() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");
        List<Message> messages=userService.OneMessage(from_id,user.getId());
        //将消息标记为已读
        userService.HadRead(from_id,user.getId());
        if (messages.size()>0){
            //写给前端未读信息   json格式数据
            JsonConfig jsonConfig = new JsonConfig();
            //过滤掉user的密码
            jsonConfig.setExcludes(new String[]{"password"});
            JSONArray jsonObject = JSONArray.fromObject(messages,jsonConfig);

            //json数据写给浏览器
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
        }
    }
    //进入私信界面
    public String ToMessage(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //查询两个人之间的全部私信
        List<Message> messageList=userService.QueryAllMessage(from_id,user.getId());
        //id查询出user
        User codeuser = userService.GetUserById(from_id);
        //将消息标记为已读
        userService.HadRead(from_id,user.getId());
        //保存到域中
        ActionContext.getContext().getSession().put("messageList",messageList);
        ActionContext.getContext().getSession().put("codeuser",codeuser);
        return "ToMessage";
    }
    //保存私信图片
    public void Mespic() throws IOException {
        //处理上传的文件
        if(upload!=null){
            //获取文件扩展名
            int index = uploadFileName.lastIndexOf(".");
            String exName = uploadFileName.substring(index);
            //随机生成文件名
            String uuid = UUID.randomUUID().toString();
            //将生成的uuid中的"-"去掉，并拼接扩展名
            String fileName = uuid.replace("-", "") + exName;

            //新建服务器接受文件的目录
            String realPath = ServletActionContext.getServletContext().getRealPath("/messagepic");
            //路径转文件
            File file = new File(realPath);
            //如果文件不存在，新建文件夹
            if(!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File newFile = new File(realPath + "/" + fileName);
            //把临时文件copy过来
            FileUtil.copyFile(upload,newFile);

            User user =(User) ActionContext.getContext().getSession().get("user");
            //查询出要发送的人
            User toUser=userService.GetUserById(to_id);
            //设置参数
            message.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            message.setFromUser(user);
            message.setToUser(toUser);
            message.setContext(fileName);
            message.setStatus(0);
            //保存到数据库
            userService.SaveMessage(message);

            //数据返回给前端
            //写给前端未读信息   json格式数据
            JsonConfig jsonConfig = new JsonConfig();
            //过滤掉user的密码
            jsonConfig.setExcludes(new String[]{"fromUser","toUser"});
            JSONObject jsonObject = JSONObject.fromObject(message,jsonConfig);
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
        }
    }

    //查询最近三天的信息
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
        //保存到域中
        ActionContext.getContext().getSession().put("threeMessage",threeMessage);
        ActionContext.getContext().getSession().put("WeekMessage",WeekMessage);
        ActionContext.getContext().getSession().put("BeforeMessage",BeforeMessage);
        ActionContext.getContext().getSession().put("weekmes",weekmes);
        ActionContext.getContext().getSession().put("beforemes",beforemes);
        return "threeMessage";
    }

    //搜索私信
    public void search() throws IOException {
        //username查询用户
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
