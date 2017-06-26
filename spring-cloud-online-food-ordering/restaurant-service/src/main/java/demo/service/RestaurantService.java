package demo.service;

import demo.domain.RestaurantInfo;

import java.util.List;

public interface RestaurantService {
    List<RestaurantInfo > saveRestaurantInfos(List<RestaurantInfo> restaurantInfos);
    void deleteAllRestaurants();
    List<RestaurantInfo> findByRestaurantName(String name);
    RestaurantInfo findByRestaurantId(String restaurantId);
}
