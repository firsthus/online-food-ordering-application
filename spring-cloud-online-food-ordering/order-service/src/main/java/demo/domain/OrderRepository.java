package demo.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInfo, Long>{
    List<OrderInfo> findByRestaurantId(String restaurantId);
    void deleteByOrderId(String OrderId);
}
