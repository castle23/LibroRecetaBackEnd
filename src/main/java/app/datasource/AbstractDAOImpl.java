package app.datasource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by carlos on 16/05/18.
 */
@Repository
@Transactional
class AbstractDAOImpl implements AbstractDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void persist(Object entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void persist(Object[] entities) {
        for (Object entity : entities) {
            persist(entity);
        }
    }

    <T> List<T> findAll(Class<T> entityClass) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<T>) criteria.list();
    }

    <T> T load(Class<T> entityClass, long id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    <T> List<T> findListNamedQuery( String namedQuery, Map<String,Object> params) {
         Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQuery);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<T>) query.list();
    }
}
