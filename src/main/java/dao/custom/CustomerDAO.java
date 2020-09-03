package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public  String getLastCustomerId() throws Exception;
}
