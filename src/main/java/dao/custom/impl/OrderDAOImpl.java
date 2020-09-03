package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.OrderDAO;
import entity.Order;
import org.hibernate.Session;

import java.util.List;

public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {


    @Override
    public String getLastOrderId() throws Exception {
        return (String) session.createQuery("SELECT o.id FROM entity.Order o ORDER BY o.id DESC").setMaxResults(1).list().get(0);
    }

}
