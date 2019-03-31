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

    //��¼���û����������¼
    public User LoginGetUser(User user) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("password", user.getPassword()));
        //�ж������仹���û�����¼
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
        //�ж������仹���û�����¼
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

    //��ȡ�������д���ֿ�
    public List<Repertory> GetRepertory(Integer id) {

        return userDao.GetRepertory(id);
    }

    //��ȡ�������д���ģ��
    public List<CodeModule> CodeModule(Integer id) {
        return userDao.CodeModule(id);
    }

    //����id��ѯ�û�
    public User GetUserById(Integer to_id) {
        return userDao.GetUserById(to_id);
    }

    //����Ϣ���浽���ݿ�
    public void SaveMessage(Message message) {
        userDao.SaveMessage(message);
    }

    //��ѯ���˷����ҵ�δ��˽��
    public List<Message> QueryMessage(Integer id) {
        return userDao.QueryMessage(id);
    }

    //��ѯ������֮���ȫ��˽��
    public List<Message> QueryAllMessage(Integer from_id, Integer id) {
        return userDao.QueryAllMessage(from_id,id);
    }

    //����Ϣ���Ϊ�Ѷ�
    public void HadRead(Integer from_id, Integer id) {
        userDao.HadRead(from_id,id);
    }

    //��ѯָ���û����ҷ���˽��
    public List<Message> OneMessage(Integer from_id, Integer id) {
        return userDao.OneMessage(from_id,id);
    }

    //�������˽��
    public List<Message> threeMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 3);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        List<Message> messages=userDao.threeMessage(id,dateBefore,date);
        return messages;
    }

    //���һ��˽��
    public List<Message> WeekMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 7);
        String dateBefore2 = BeforeTime.getDateBefore(new Date(), 3);
        List<Message> messages=userDao.threeMessage(id,dateBefore,dateBefore2);
        return messages;
    }

    //����֮ǰ˽��
    public List<Message> BeforeMessage(Integer id) {
        String dateBefore = BeforeTime.getDateBefore(new Date(), 7);
        List<Message> messages=userDao.BeforeMessage(id,dateBefore);
        return messages;
    }

    //username��ѯ�û�
    public Integer search(String username) {
        return userDao.search(username);
    }
}
