package dao;

import entity.Item;
import entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO <T extends SuperEntity,ID extends Serializable>{
    public void setSession(Session session);

}
