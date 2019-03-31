package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.CodeService;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderAction extends ActionSupport {

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "orderModule")
    private OrderModule orderModule;

    @Resource(name = "likeCode")
    private LikeCode likeCode;

    @Setter
    private Integer id;
    //跳转到购买页面
    public String ToBuy(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id查询代码模块
        CodeModule codeModule = codeService.FindCodeById(id);
        //查询出代码模块的用户
        User userById = codeService.findUserById(codeModule.getUser_id());
        //需要查询出两方的购买次数
        Integer Usercount=codeService.OrderCount(user.getId());
        Integer Sellercount=codeService.OrderCount(codeModule.getUser_id());
        //存储数据
        ActionContext.getContext().getSession().put("codeModule",codeModule);
        ActionContext.getContext().getSession().put("userById",userById);
        ActionContext.getContext().getSession().put("Usercount",Usercount);
        ActionContext.getContext().getSession().put("Sellercount",Sellercount);
        return "ToBuy";
    }

    @Setter
    private String seller_name;

    //购买
    public String buyCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id查询代码模块
        CodeModule codeModule = codeService.FindCodeById(id);
        //设置数据
        orderModule.setCodeModule(codeModule);
        orderModule.setUser_id(user.getId());
        orderModule.setSeller_name(seller_name);
        orderModule.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //保存到数据库
        codeService.SaveOrder(orderModule);
        return "buyCode";
    }

    @Setter
    private Integer currentPage;
    //跳转到个人购买的代码模块中
    public String BuyModule(){
        User user = (User) ActionContext.getContext().getSession().get("user");
        //查询个人所有代码模块，封装成PageBean
        PageBean<OrderModule> orderModulePageBean=codeService.BuyModule(user.getId(),currentPage);
        //PageBean存到Session中
        ActionContext.getContext().getSession().put("pageBean",orderModulePageBean);
        return "BuyModule";
    }

    //查询是否已经购买
    public void IsBuy() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //根据用户id和代码模块id查询是否有订单
        String result=codeService.IsBuy(user.getId(),id);
        //数据写给前端
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(result);
    }

    //查询个人收藏代码模块
    public String likeCode(){
        User user = (User) ActionContext.getContext().getSession().get("user");
        //查询个人所有代码模块，封装成PageBean
        PageBean<LikeCode> likeCodePageBean=codeService.likeCode(user.getId(),currentPage);
        //PageBean存到Session中
        ActionContext.getContext().getSession().put("pageBean",likeCodePageBean);
        return "likeCode";
    }

    //收藏代码
    public void AddLikeCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id查询代码模块
        CodeModule codeModule = codeService.FindCodeById(id);
        //设置数据
        likeCode.setCodeModule(codeModule);
        likeCode.setUser_id(user.getId());
        likeCode.setLiketime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //保存到数据库
        codeService.AddLikeCode(likeCode);
    }
    //查询是否收藏
    public void IsLike() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //根据用户id和代码模块id查询是否有订单
        String islike=codeService.IsLike(user.getId(),id);
        //数据写给前端
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(islike);
    }
    //取消收藏
    public void DeleteLikeCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //根据id查询出收藏的代码模块
        LikeCode likeCode=codeService.FindLikeCode(user.getId(),id);
        codeService.DeleteLikeCode(likeCode);
    }
}
