package demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantInfo, Long> {

    RestaurantInfo findFirstByRestaurantId(@Param("restaurantID") String restaurantId);
    List<RestaurantInfo> findByRestaurantName(@Param("retaurantName") String restaurantName);
    void deleteAll();
}
