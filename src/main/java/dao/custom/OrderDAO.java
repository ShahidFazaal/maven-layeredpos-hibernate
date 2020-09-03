package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Order;

import java.util.List;

public interface OrderDAO extends CrudDAO<Order,String> {
    public  String getLastOrderId() throws Exception;
}
