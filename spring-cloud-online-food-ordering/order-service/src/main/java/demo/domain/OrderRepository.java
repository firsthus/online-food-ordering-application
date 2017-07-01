package demo.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInfo, Long>{
    List<OrderInfo> findByRestaurantId(String restaurantId);
    OrderInfo findFirstByOrderId(String orderId);
    void deleteByOrderId(String OrderId);
}
