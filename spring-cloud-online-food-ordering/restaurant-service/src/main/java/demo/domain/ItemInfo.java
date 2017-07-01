package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="menuItem")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfo{

    @Id
    @GeneratedValue
    private Long Id;

    private String foodId;
    private String name;
    private double price;
    private String description;
    private String restaurantId;

    @ManyToOne
    private RestaurantInfo restaurantInfo;

    @JsonCreator
    public ItemInfo(@JsonProperty("Id") Long Id) {
        this.Id = Id;
    }

    public ItemInfo(String name, double price, String description, String restaurantId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurantId = restaurantId;
        this.foodId = String.valueOf((name+price+description).hashCode());
    }

    public ItemInfo(String foodId, String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.foodId = String.valueOf((name+price+description).hashCode());
    }

    public ItemInfo(String name){
        this.name = name;
    }
}
