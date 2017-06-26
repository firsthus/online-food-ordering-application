package demo.service.impl;

import demo.domain.RestaurantInfo;
import demo.domain.RestaurantRepository;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;


    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantInfo> saveRestaurantInfos(List<RestaurantInfo> restaurantInfos) {
        return restaurantRepository.save(restaurantInfos);
    }

    @Override
    public void deleteAllRestaurants() {
        restaurantRepository.deleteAll();
    }

    @Override
    public List<RestaurantInfo> findByRestaurantName(String name) {
        return restaurantRepository.findByRestaurantName(name);
    }

    @Override
    public RestaurantInfo findByRestaurantId(String restaurantId) {
        return restaurantRepository.findFirstByRestaurantId(restaurantId);
    }


}
