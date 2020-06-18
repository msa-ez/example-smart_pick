package smartpick;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="SmartDelivery_table")
public class SmartDelivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long storeId;
    private String productName;
    private Long qty;
    private String status;
    private String receptionDate;
    private String deliveryDate;

    @PostUpdate
    public void onPostUpdate(){
        if ("DELIVERED".equals(this.status)){
            Delivered delivered = new Delivered();
            BeanUtils.copyProperties(this, delivered);
            delivered.publishAfterCommit();
        }

    }

    public SmartDelivery(Ordered ordered){
        this.orderId = ordered.getId();
        this.storeId = ordered.getStoreId();
        this.productName = ordered.getProductName();
        this.qty = ordered.getQty();
        this.status = "PRODUCT READY";
        this.receptionDate = ordered.getOrderDate();
    }

    public SmartDelivery(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }




}
