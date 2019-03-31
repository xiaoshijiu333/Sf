package dao;

import java.util.List;

import model.CodeModule;
import model.Message;
import model.Repertory;
import model.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {

    //登录
    public User LoginGetUser(DetachedCriteria detachedCriteria) {
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //注册保存用户
    public void AddUser(User user) {
        this.getHibernateTemplate().save(user);
    }

    //更新用户
    public void UpdateUser(User user) {
        this.getHibernateTemplate().update(user);
    }

    //查询代码库
    public List<Repertory> GetRepertory(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.add(Restrictions.eq("user_id",id));
        List<Repertory> list = (List<Repertory>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //查询个人3个最新代码模块
    public List<CodeModule> CodeModule(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.addOrder(Order.desc("public_time"));
        detachedCriteria.add(Restrictions.eq("user_id",id));
        List<CodeModule> list = (List<CodeModule>)this.getHibernateTemplate().findByCriteria(detachedCriteria,0,3);
        return list;
    }

    //根据id查询用户
    public User GetUserById(Integer to_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("id", to_id));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //将信息保存到数据库
    public void SaveMessage(Message message) {
        this.getHibernateTemplate().save(message);
    }

    //查询别人发给我的未读的私信
    public List<Message> QueryMessage(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("toUser.id", id));
        detachedCriteria.add(Restrictions.eq("status", 0));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }
    //或者条件查询两个人之间的所有私信
    public List<Message> QueryAllMessage(Integer from_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.or(Restrictions.eq("fromUser.id",from_id),
                Restrictions.eq("toUser.id",from_id)));
        detachedCriteria.add(Restrictions.or(Restrictions.eq("fromUser.id",id),
                Restrictions.eq("toUser.id",id)));
        detachedCriteria.addOrder(Order.asc("time"));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //将消息标记为已读
    public void HadRead(Integer from_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("fromUser.id", from_id));
        detachedCriteria.add(Restrictions.eq("toUser.id", id));
        detachedCriteria.add(Restrictions.eq("status", 0));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            for (Message message : list) {
                message.setStatus(1);
                this.getHibernateTemplate().update(message);
            }
        }
    }

    //查询指定用户给我发的私信
    public List<Message> OneMessage(Integer from_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("fromUser.id", from_id));
        detachedCriteria.add(Restrictions.eq("toUser.id", id));
        detachedCriteria.add(Restrictions.eq("status", 0));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //最近三天私信
    public List<Message> threeMessage(Integer id, String dateBefore,String date) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("toUser.id", id));
        detachedCriteria.add(Restrictions.between("time",dateBefore,date));
        detachedCriteria.addOrder(Order.desc("id"));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //更久之前私信
    public List<Message> BeforeMessage(Integer id, String date) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        detachedCriteria.add(Restrictions.eq("toUser.id", id));
        detachedCriteria.add(Restrictions.le("time",date));
        detachedCriteria.addOrder(Order.desc("id"));
        List<Message> list = (List<Message>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //username查询用户
    public Integer search(String username) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username", username));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0).getId();
        }
        return null;
    }
}
