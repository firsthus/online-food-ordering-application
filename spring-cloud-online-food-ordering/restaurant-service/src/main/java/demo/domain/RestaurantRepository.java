package demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantInfo, Long> {

    RestaurantInfo findFirstByRestaurantId(@Param("restaurantID") String restaurantId);
    RestaurantInfo findFirstByRestaurantName(@Param("restaurantName") String restaurantName);
    List<RestaurantInfo> findByRestaurantName(@Param("restaurantName") String restaurantName);
    void deleteAll();
}
