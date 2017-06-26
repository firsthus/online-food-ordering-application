package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "RestaurantInfos")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {
    @Id
    @GeneratedValue
    private Long Id;

    private String restaurantId;

    @JsonProperty("name")
    private String restaurantName;

    private String address;

    @JsonProperty("phone")
    private String phoneNumber;

    private Date timestamp = new Date();

    @JsonProperty("menu")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
    private List<ItemInfo> menu = new ArrayList<>();

    @JsonCreator
    public RestaurantInfo(@JsonProperty("Id") Long Id) {
        this.Id = Id;
    }

    //@JsonCreator
    public RestaurantInfo(List<ItemInfo> menu) {
        this.menu = menu;
    }

}
