package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {


    @Override
    public String getLastCustomerId() throws Exception {
        return (String) session.createNativeQuery("SELECT c.id FROM Customer c ORDER BY id DESC LIMIT 1").uniqueResult();
//            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC LIMIT 1");
//            if (!rst.next()){
//                return null;
//            }else{
//                return rst.getString(1);
//            }
    }

}
