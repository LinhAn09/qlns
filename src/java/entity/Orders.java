package entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;



//1 tự viết thì code đẹp

@Entity
@Table(name = "Orders")
public class Orders {
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int orderID; // khóa chính
  //  @Temporal(TemporalType.DATE)
   // @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String oDate;
    private int CustomID;

    public Orders() {
    }

    public Orders(int orderID, String oDate, int CustomID) {
        this.orderID = orderID;
        this.oDate = oDate;
        this.CustomID = CustomID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public int getCustomID() {
        return CustomID;
    }

    public void setCustomID(int CustomID) {
        this.CustomID = CustomID;
    }

   
    
}
