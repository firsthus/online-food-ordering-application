package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.domain.OrderInfo;
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
@Entity(name="orderItem")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String foodId;
    private String name;
    private double price;
    private String description;
    private int quantity;


    @ManyToOne
    private OrderInfo orderInfo;

}
