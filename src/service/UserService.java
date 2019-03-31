package service;

import Tools.BeforeTime;
import Tools.isEmail;
import model.CodeModule;
import model.Message;
import model.Repertory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;


import dao.UserDao;
import model.User;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
public class UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    //登录，用户名和密码登录
    public User LoginGetUser(User user) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("password", user.getPassword()));
        //判断是邮箱还是用户名登录
        if(isEmail.isemail(user.getUsername())){
            detachedCriteria.add(Restrictions.eq("email", user.getUsername()));
        }else{
            detachedCriteria.add(Restrictions.eq("username", user.getUsername()));
        }
        return userDao.LoginGetUser(detachedCriteria);
    }

    public void Register(User user) {
        userDao.AddUser(user);
    }

    public String OneUname(String username) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        //判断是邮箱还是用户名登录
        if(isEmail.isemail(username)){
            detachedCriteria.add(Restrictions.eq("email", username));
        }else{
            detachedCriteria.add(Restrictions.eq("username", username));
        }
        User loginGetUser = userDao.LoginGetUser(detachedCriteria);
        if (loginGetUser!=null){
            return "oneUname";
        }else {
            return null;
        }
    }

    public void UpdateUser(User user) {
        userDao.UpdateUser(user);
    }

    //获取个人所有代码仓库
    public List<Repertory> GetRepertory(Integer id) {

        return userDao.GetRepertory(id);
    }

    //获取个人所有代码模块
    public List<CodeModule> CodeModule(Integer id) {
        return userDao.CodeModule(id);
    }

    //根据id查询用户
    public User GetUserById(Integer to_id) {
        return userDao.GetUserById(to_id);
    }

    //将信息保存到数据库
    public void SaveMessage(Message message) {
        userDao.SaveMessage(message);
    }

    //查询别人发给我的未读私信
    public List<Message> QueryMessage(Integer id) {
        return userDao.QueryMessage(id);
    }

    //查询两个人之间的全部私信
    public List<Message> QueryAllMessage(Integer from_id, Integer id) {
        return userDao.QueryAllMessage(from_id,id);
    }

    //将消息标记为已读
    public void HadRead(Integer from_id, Integer id) {
        userDao.HadRead(from_id,id);
    }

    //查询指定用户给我发的私信
    public List<Message> OneMessage(Integer from_id, Integer id) {
        return userDao.OneMessage(from_id,id);
    }

    //最近三天私信
    public List<Message> threeMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 3);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        List<Message> messages=userDao.threeMessage(id,dateBefore,date);
        return messages;
    }

    //最近一周私信
    public List<Message> WeekMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 7);
        String dateBefore2 = BeforeTime.getDateBefore(new Date(), 3);
        List<Message> messages=userDao.threeMessage(id,dateBefore,dateBefore2);
        return messages;
    }

    //更久之前私信
    public List<Message> BeforeMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 7);
        List<Message> messages=userDao.BeforeMessage(id,dateBefore);
        return messages;
    }

    //username查询用户
    public Integer search(String username) {
        return userDao.search(username);
    }
}
