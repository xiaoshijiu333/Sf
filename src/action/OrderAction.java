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
    //��ת������ҳ��
    public String ToBuy(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id��ѯ����ģ��
        CodeModule codeModule = codeService.FindCodeById(id);
        //��ѯ������ģ����û�
        User userById = codeService.findUserById(codeModule.getUser_id());
        //��Ҫ��ѯ�������Ĺ������
        Integer Usercount=codeService.OrderCount(user.getId());
        Integer Sellercount=codeService.OrderCount(codeModule.getUser_id());
        //�洢����
        ActionContext.getContext().getSession().put("codeModule",codeModule);
        ActionContext.getContext().getSession().put("userById",userById);
        ActionContext.getContext().getSession().put("Usercount",Usercount);
        ActionContext.getContext().getSession().put("Sellercount",Sellercount);
        return "ToBuy";
    }

    @Setter
    private String seller_name;

    //����
    public String buyCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id��ѯ����ģ��
        CodeModule codeModule = codeService.FindCodeById(id);
        //��������
        orderModule.setCodeModule(codeModule);
        orderModule.setUser_id(user.getId());
        orderModule.setSeller_name(seller_name);
        orderModule.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //���浽���ݿ�
        codeService.SaveOrder(orderModule);
        return "buyCode";
    }

    @Setter
    private Integer currentPage;
    //��ת�����˹���Ĵ���ģ����
    public String BuyModule(){
        User user = (User) ActionContext.getContext().getSession().get("user");
        //��ѯ�������д���ģ�飬��װ��PageBean
        PageBean<OrderModule> orderModulePageBean=codeService.BuyModule(user.getId(),currentPage);
        //PageBean�浽Session��
        ActionContext.getContext().getSession().put("pageBean",orderModulePageBean);
        return "BuyModule";
    }

    //��ѯ�Ƿ��Ѿ�����
    public void IsBuy() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //�����û�id�ʹ���ģ��id��ѯ�Ƿ��ж���
        String result=codeService.IsBuy(user.getId(),id);
        //����д��ǰ��
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(result);
    }

    //��ѯ�����ղش���ģ��
    public String likeCode(){
        User user = (User) ActionContext.getContext().getSession().get("user");
        //��ѯ�������д���ģ�飬��װ��PageBean
        PageBean<LikeCode> likeCodePageBean=codeService.likeCode(user.getId(),currentPage);
        //PageBean�浽Session��
        ActionContext.getContext().getSession().put("pageBean",likeCodePageBean);
        return "likeCode";
    }

    //�ղش���
    public void AddLikeCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //id��ѯ����ģ��
        CodeModule codeModule = codeService.FindCodeById(id);
        //��������
        likeCode.setCodeModule(codeModule);
        likeCode.setUser_id(user.getId());
        likeCode.setLiketime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //���浽���ݿ�
        codeService.AddLikeCode(likeCode);
    }
    //��ѯ�Ƿ��ղ�
    public void IsLike() throws IOException {
        User user = (User) ActionContext.getContext().getSession().get("user");
        //�����û�id�ʹ���ģ��id��ѯ�Ƿ��ж���
        String islike=codeService.IsLike(user.getId(),id);
        //����д��ǰ��
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(islike);
    }
    //ȡ���ղ�
    public void DeleteLikeCode(){
        User user =(User) ActionContext.getContext().getSession().get("user");
        //����id��ѯ���ղصĴ���ģ��
        LikeCode likeCode=codeService.FindLikeCode(user.getId(),id);
        codeService.DeleteLikeCode(likeCode);
    }
}
