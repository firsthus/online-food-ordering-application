package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Long id;

    private String foodId;
    private String name;
    private double price;
    private String description;
    private String restaurantId;

    @ManyToOne
    private RestaurantInfo restaurantInfo;
}
