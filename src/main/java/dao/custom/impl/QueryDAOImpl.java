package dao.custom.impl;

import dao.custom.QueryDAO;
import entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    private Session session;

    @Override
    public CustomEntity getOrderDetail(String orderId) throws Exception {
        return (CustomEntity) session.createNativeQuery("SELECT o.id AS orderId,c.name AS customerName,o.date AS orderDate  FROM `Order`o INNER JOIN Customer c ON o.customerId = c.id WHERE o.id =?1").setParameter(1, orderId).setResultTransformer(Transformers.aliasToBean(CustomEntity.class)).uniqueResult();

    }


    @Override
    public CustomEntity getOrderDetail2(String orderId) throws Exception {
        return (CustomEntity) session.createNativeQuery("SELECT o.id,c.name,c.id FROM `Order`o INNER JOIN Customer c ON o.customerId = c.id WHERE o.id=:id").setParameter("id", orderId).setResultTransformer(Transformers.aliasToBean(CustomEntity.class)).uniqueResult();

    }

    @Override
    public List<CustomEntity> getAllDetails() throws Exception {
        return session.createNativeQuery("SELECT o.id AS orderId,o.date AS orderDate,c.name AS customerName,c.id AS customerId,SUM(od.qty * od.unitPrice) AS total FROM `Order`o INNER JOIN Customer c ON o.customerId = c.id INNER JOIN OrderDetail od on o.id = od.orderId GROUP BY 1").setResultTransformer(Transformers.aliasToBean(CustomEntity.class)).list();
         /*   ResultSet resultSet = CrudUtil.execute("SELECT o.id,o.date,c.name,c.id,SUM(od.qty * od.unitPrice) as 'total' FROM `Order`o INNER JOIN Customer c ON o.customerId = c.id INNER JOIN OrderDetail od on o.id = od.orderId GROUP BY 1");
            ArrayList<CustomEntity> allOrders = new ArrayList<>();
            while (resultSet.next()){
                allOrders.add(new CustomEntity(resultSet.getString(1),resultSet.getString(3),resultSet.getDate(2),resultSet.getString(4),resultSet.getInt(5)));
            }
            return allOrders;*/

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
