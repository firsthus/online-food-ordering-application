package demo.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Table(name = "order")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderInfo {

    enum OrderStatus {
        processing, delivering, delivered, cancelled, other
    }

    @Id
    @GeneratedValue
    private Long Id;

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("restaurantId")
    private String restaurantId;

    @JsonProperty("notes")
    private String notes;

    private final Date timestamp = new Date();

    @OneToMany(mappedBy = "orderInfo")
    private List<Item> foodList = new ArrayList<>();
    //@OneToMany(mappedBy = "orderInfo")
    //private List<Integer> quantities = new ArrayList<>();

    private boolean isPaid;

    private OrderStatus orderStatus;

    public OrderInfo() {

    }



    @JsonCreator
    public OrderInfo(@JsonProperty("userId") String userId, @JsonProperty("restaurantId") String restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

}
