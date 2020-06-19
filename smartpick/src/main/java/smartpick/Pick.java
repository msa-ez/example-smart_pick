package smartpick;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Pick_table")
public class Pick {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long storeId;
    private String productName;
    private Long qty;
    private String status;
    private String deliveredDate;

    @PostUpdate
    public void onPostUpdate(){
        if ("CONFIRMED".equals(this.status)){
            Confirmed confirmed = new Confirmed();
            BeanUtils.copyProperties(this, confirmed);
            confirmed.publishAfterCommit();
        } else if ("PICKED".equals(this.status)){
            Picked picked = new Picked();
            BeanUtils.copyProperties(this, picked);
            picked.publishAfterCommit();
        } else if ("CANCELED".equals(this.status)){
            PickCanceled pickCanceled = new PickCanceled();
            BeanUtils.copyProperties(this, pickCanceled);
            pickCanceled.publishAfterCommit();
        }
    }

    public Pick(Delivered delivered){
        this.orderId = delivered.getOrderId();
        this.storeId = delivered.getStoreId();
        this.productName = delivered.getProductName();
        this.qty = delivered.getQty();
        this.status = "PRODUCT ARRIVED";
        this.deliveredDate = delivered.getDeliveryDate();
    }

    public Pick(){

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
    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }




}
