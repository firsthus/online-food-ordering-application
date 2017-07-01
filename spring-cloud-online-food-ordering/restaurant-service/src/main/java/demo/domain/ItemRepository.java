package demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemInfo, Long> {
    List<ItemInfo> findByRestaurantId(String restaurantId);
    ItemInfo findFirstByName(String name);
}
