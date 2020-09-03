package business.custom.impl;

import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import db.HibernateUtil;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.CustomerTM;
import util.ItemTM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
    public  String getNewItemCode() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);

        String lastItemCode = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            lastItemCode = itemDAO.getLastItemCode();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

            if (lastItemCode == null) {
                return "I001";
            } else {
                int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "I00" + maxId;
                } else if (maxId < 100) {
                    id = "I0" + maxId;
                } else {
                    id = "I" + maxId;
                }
                return id;
            }
    }
    public  List<ItemTM> getAllItems() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        List<Item> allItems = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            allItems = itemDAO.findAll();

            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

            ArrayList<ItemTM> items = new ArrayList<>();
            for (Object entity : allItems) {
                Item item = (Item) entity;
                items.add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice().doubleValue()));
            }
            return items;
    }
    public void saveItem(String code, String description, int qtyOnHand, BigDecimal unitPrice) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            itemDAO.save(new Item(code, description, unitPrice, qtyOnHand));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
    }
    public void deleteItem(String itemCode) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            itemDAO.delete(itemCode);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

            return ;

    }
    public void updateItem(String description, int qtyOnHand, BigDecimal unitPrice, String itemCode) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            itemDAO.update(new Item(itemCode, description, unitPrice, qtyOnHand));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }


}
