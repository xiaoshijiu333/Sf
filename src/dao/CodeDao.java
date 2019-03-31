package dao;

import model.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CodeDao extends HibernateDaoSupport {

    //��������ֿ�
    public void Saverep(Repertory repertory) {
        this.getHibernateTemplate().save(repertory);
    }

    //�½�����ģ��
    public void SaveCodeModule(CodeModule codeModule) {
        this.getHibernateTemplate().save(codeModule);
    }

    //��ѯ���˴���ģ������
    public Integer GetModuleById(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //��ҳ��ѯ���˴���ģ��
    public List<CodeModule> GetMyModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.addOrder(Order.desc("public_time"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //����id��ѯ����ģ��
    public CodeModule FindCodeById(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.add(Restrictions.eq("id", id));
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    //ɾ������ģ��
    public void deleteCode(CodeModule codeModule) {
        this.getHibernateTemplate().delete(codeModule);
    }

    //�޸Ĵ���ģ��
    public void updateCodeModule(CodeModule codeModule) {
        this.getHibernateTemplate().update(codeModule);
    }

    //�õ����д���ģ�������
    public Integer GetModuleCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //��ҳ��ѯ���д���ģ��
    public List<CodeModule> GetAllModule(Integer index, Integer pageSize, DetachedCriteria detachedCriteria) {
        //��ղ�ѯ�ܼ�¼��������
        detachedCriteria.setProjection(null);
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //��id��ѯ�û�
    public User findUserById(Integer user_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("id", user_id));
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    //����id��ѯ�������
    public Integer OrderCount(Integer user_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderModule.class);
        detachedCriteria.setProjection(Projections.rowCount());
        detachedCriteria.add(Restrictions.eq("user_id", user_id));
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //���湺���¼
    public void SaveOrder(OrderModule orderModule) {
        this.getHibernateTemplate().save(orderModule);
    }

    //��ҳ��ѯ����Ĵ���ģ��
    public List<OrderModule> GetBuyModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderModule.class);
        detachedCriteria.addOrder(Order.desc("time"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<OrderModule> list = (List<OrderModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //��ѯ�Ƿ��Ѿ�����
    public Boolean IsBuy(Integer user_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderModule.class);
        detachedCriteria.add(Restrictions.eq("user_id", user_id));
        detachedCriteria.add(Restrictions.eq("codeModule.id", id));
        List<OrderModule> list = (List<OrderModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //����id��ѯ�ղؼ�¼����
    public Integer LikeCount(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LikeCode.class);
        detachedCriteria.setProjection(Projections.rowCount());
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //��ҳ��ѯ�ղصĴ���ģ��
    public List<LikeCode> GetLikeModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LikeCode.class);
        detachedCriteria.addOrder(Order.desc("liketime"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<LikeCode> list = (List<LikeCode>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //����ղش���ģ��
    public void AddLikeCode(LikeCode likeCode) {
        this.getHibernateTemplate().save(likeCode);
    }

    //��ѯ�Ƿ��Ѿ��ղ�
    public Boolean IsLike(Integer user_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LikeCode.class);
        detachedCriteria.add(Restrictions.eq("user_id", user_id));
        detachedCriteria.add(Restrictions.eq("codeModule.id", id));
        List<LikeCode> list = (List<LikeCode>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //ȡ���ղ�
    public void DeleteLikeCode(LikeCode likeCode) {
        this.getHibernateTemplate().delete(likeCode);
    }

    //��ѯָ�����ղش���
    public LikeCode FindLikeCode(Integer user_id, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LikeCode.class);
        detachedCriteria.add(Restrictions.eq("user_id", user_id));
        detachedCriteria.add(Restrictions.eq("codeModule.id", id));
        List<LikeCode> list = (List<LikeCode>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    //��ѯ����project
    public List<Repertory> project(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //��ҳ��ѯ����project(public)����
    public Integer projectCount(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.setProjection(Projections.rowCount());
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "public"));
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //��ҳ��ѯ����project(public)
    public List<Repertory> Getprojects(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.addOrder(Order.desc("create_date"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "public"));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //��ҳ��ѯ����project(private)����
    public Integer projectCount2(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.setProjection(Projections.rowCount());
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "private"));
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //��ҳ��ѯ����project(private)
    public List<Repertory> Getprojects2(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.addOrder(Order.desc("create_date"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "private"));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }
}
