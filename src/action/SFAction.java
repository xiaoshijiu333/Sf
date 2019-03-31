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

    //��ת��AQ
    public String aq(){
        return "aq";
    }

    //��ת��contact
    public String contact(){
        return "contact";
    }

    //��¼
    public void login() throws IOException {

        User loginUser=userService.LoginGetUser(user1);

        if (loginUser == null) {
            //��Ϊ��Ajax��������Ҫ������Ӧ����
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print("err");
        } else {
            //���浽Session���У���Ajax������ת
            ActionContext.getContext().getSession().put("user", loginUser);
        }
    }

    //ע��
    public String register(){
        userService.Register(user1);
        //session���д洢
        ActionContext.getContext().getSession().put("user", user1);
        return SUCCESS;
    }

    //�û���(����)Ψһ�ļ��
    public void OneUname() throws IOException {
        User user =(User) ActionContext.getContext().getSession().get("user");

        //����ע����޸�
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
