package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfoDto {
    private String restaurantName;
    private String userId;
    private int deliveryTime;
    private String deliveryAddress;
    private String notes;
    private String orderId;
    private double price;
    private List<Item> itemList = new ArrayList<>();
    private OrderInfo.OrderStatus orderStatus;
            //= new ArrayList<>();


    public OrderInfoDto(OrderInfo orderInfo) {
        this.userId = orderInfo.getUserId();
        this.deliveryAddress = orderInfo.getDeliveryAddress();
        this.notes = orderInfo.getNotes();
        this.orderId = orderInfo.getOrderId();
        this.restaurantName = orderInfo.getRestaurantName();
        this.orderStatus = orderInfo.getOrderStatus();
        this.price = 0.0;
    }
}
