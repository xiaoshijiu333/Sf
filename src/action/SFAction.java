package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import model.CodeModule;
import model.Repertory;
import model.User;
import org.apache.struts2.ServletActionContext;
import service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class SFAction extends ActionSupport implements ModelDriven<User> {

    @Resource(name = "user")
    private User user1;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public User getModel() {
        return user1;
    }

    //跳转到AQ
    public String aq(){
        return "aq";
    }

    //跳转到contact
    public String contact(){
        return "contact";
    }

    //登录
    public void login() throws IOException {

        User loginUser=userService.LoginGetUser(user1);

        if (loginUser == null) {
            //因为是Ajax请求，所以要设置响应内容
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print("err");
        } else {
            //保存到Session域中，用Ajax进行跳转
            ActionContext.getContext().getSession().put("user", loginUser);
        }
    }

    //注册
    public String register(){
        userService.Register(user1);
        //session域中存储
        ActionContext.getContext().getSession().put("user", user1);
        return SUCCESS;
    }

    //用户名(邮箱)唯一的检测
    public void OneUname() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");

        //区别注册和修改
        if(user==null){
            String s = userService.OneUname(user1.getUsername());
            if (s!=null){
                ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
                ServletActionContext.getResponse().getWriter().print("oneUname");
            }
        }else{

            if(!user1.getUsername().equals(user.getUsername()) && !user1.getUsername().equals(user.getEmail())){
                String s = userService.OneUname(user1.getUsername());
                if(s!=null){
                    ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
                    ServletActionContext.getResponse().getWriter().print("oneUname");
                }
            }
        }
    }
}
