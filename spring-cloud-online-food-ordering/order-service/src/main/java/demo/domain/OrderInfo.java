package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Date;


@Entity
@Table(name = "orderInfo")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderInfo {

    public enum OrderStatus {
        processing, paid, failed, delivering, delivered, cancelled, other
    }

    @Id
    @GeneratedValue
    private Long Id;
    private String orderId;
    private String restaurantId;
    private String userId;
    private String notes;
    private String restaurantName;
    private String deliveryAddress;


    //@OneToMany(mappedBy = "orderInfo")

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orderInfo")
    private List<Item> foodList;

    private final Date timestamp = new Date();

    private OrderStatus orderStatus;

    public OrderInfo() {
    }


    @JsonCreator
    public OrderInfo(@JsonProperty("Id") Long Id) {
        this.Id = Id;
    }

    public OrderInfo(String userId, String restaurantId, String orderId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.orderStatus = OrderStatus.processing;
    }

    public OrderInfo(String orderId) {
        this.orderId = orderId;
    }

    public OrderInfo(String orderId, String restaurantId, String userId, String notes, String restaurantName, String deliveryAddress) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.notes = notes;
        this.restaurantName = restaurantName;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = OrderStatus.processing;
    }


}
