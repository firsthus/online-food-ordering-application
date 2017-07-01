package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity(name= "orderItem")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Item {
    @Id
    @GeneratedValue
    private Long Id;

    private String foodId;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String restaurantId;

    @ManyToOne
    private OrderInfo orderInfo;


    public Item(String foodId, String name, double price, String description, int quantity, String restaurantId, OrderInfo orderInfo) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.restaurantId = restaurantId;
        this.orderInfo = orderInfo;
    }

}
