package action.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import model.User;
import org.apache.struts2.ServletActionContext;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user = (User)actionInvocation.getInvocationContext().getSession().get("user");
        if(user==null){
            //拦截，给前端验证确认框
            ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
            ServletActionContext.getResponse().getWriter().print(" <script>\n" +
                    "        var message = confirm(\"You are not logged in, click ok to log in and click cancel to return\");\n" +
                    "        if (message) {\n" +
                    "            window.location.href = \"login.html\";\n" +
                    "        } else {\n" +
                    "           window.history.back(-1);\n" +
                    "        }\n" +
                    "    </script>");
            return null;
        }else {
            //放行
            return actionInvocation.invoke();
        }
    }
}
