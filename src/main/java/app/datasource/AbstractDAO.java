package app.datasource;

/**
 * Created by carlos on 16/05/18.
 */
interface AbstractDAO {

    void persist(Object entity);

    void persist(Object[] entities);

}
