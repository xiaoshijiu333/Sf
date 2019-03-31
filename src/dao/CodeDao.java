package dao;

import model.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CodeDao extends HibernateDaoSupport {

    //创建代码仓库
    public void Saverep(Repertory repertory) {
        this.getHibernateTemplate().save(repertory);
    }

    //新建代码模块
    public void SaveCodeModule(CodeModule codeModule) {
        this.getHibernateTemplate().save(codeModule);
    }

    //查询个人代码模块数量
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

    //分页查询个人代码模块
    public List<CodeModule> GetMyModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.addOrder(Order.desc("public_time"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //根据id查询代码模块
    public CodeModule FindCodeById(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeModule.class);
        detachedCriteria.add(Restrictions.eq("id", id));
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    //删除代码模块
    public void deleteCode(CodeModule codeModule) {
        this.getHibernateTemplate().delete(codeModule);
    }

    //修改代码模块
    public void updateCodeModule(CodeModule codeModule) {
        this.getHibernateTemplate().update(codeModule);
    }

    //拿到所有代码模块的数量
    public Integer GetModuleCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    //分页查询所有代码模块
    public List<CodeModule> GetAllModule(Integer index, Integer pageSize, DetachedCriteria detachedCriteria) {
        //清空查询总记录数的条件
        detachedCriteria.setProjection(null);
        List<CodeModule> list = (List<CodeModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //根id查询用户
    public User findUserById(Integer user_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("id", user_id));
        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    //根据id查询购买次数
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

    //保存购买记录
    public void SaveOrder(OrderModule orderModule) {
        this.getHibernateTemplate().save(orderModule);
    }

    //分页查询购买的代码模块
    public List<OrderModule> GetBuyModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderModule.class);
        detachedCriteria.addOrder(Order.desc("time"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<OrderModule> list = (List<OrderModule>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //查询是否已经购买
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

    //根据id查询收藏记录次数
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

    //分页查询收藏的代码模块
    public List<LikeCode> GetLikeModule(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LikeCode.class);
        detachedCriteria.addOrder(Order.desc("liketime"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<LikeCode> list = (List<LikeCode>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //添加收藏代码模块
    public void AddLikeCode(LikeCode likeCode) {
        this.getHibernateTemplate().save(likeCode);
    }

    //查询是否已经收藏
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

    //取消收藏
    public void DeleteLikeCode(LikeCode likeCode) {
        this.getHibernateTemplate().delete(likeCode);
    }

    //查询指定的收藏代码
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

    //查询个人project
    public List<Repertory> project(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.add(Restrictions.eq("user_id", id));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    //分页查询个人project(public)总数
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

    //分页查询个人project(public)
    public List<Repertory> Getprojects(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.addOrder(Order.desc("create_date"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "public"));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    //分页查询个人project(private)总数
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

    //分页查询个人project(private)
    public List<Repertory> Getprojects2(Integer id, Integer index, Integer pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Repertory.class);
        detachedCriteria.addOrder(Order.desc("create_date"));
        detachedCriteria.add(Restrictions.eq("user_id", id));
        detachedCriteria.add(Restrictions.eq("status", "private"));
        List<Repertory> list = (List<Repertory>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }
}
