package entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class OrderDetail implements SuperEntity {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    private int qty;
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemCode",referencedColumnName = "code",insertable = false,updatable = false)
    private Item item;




    public OrderDetail(OrderDetailPK orderDetailPK, int qty, BigDecimal unitPrice) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(String orderId, String itemCode, int qty, BigDecimal unitPrice) {
        this.orderDetailPK = new OrderDetailPK(orderId,itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

}
