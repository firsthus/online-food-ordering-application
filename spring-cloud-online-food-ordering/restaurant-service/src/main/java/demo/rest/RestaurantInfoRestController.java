package demo.rest;

import demo.domain.RestaurantInfo;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class RestaurantInfoRestController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantInfoRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadRestaurant(@RequestBody List<RestaurantInfo> restaurantInfos) {
        this.restaurantService.saveRestaurantInfos(restaurantInfos);
        System.out.println("restaurantInfos saved");
    }


    @RequestMapping(value = "/restaurant", method = RequestMethod.DELETE)
    public void purge() {
        this.restaurantService.deleteAllRestaurants();
    }

    @RequestMapping(value = "/restaurant/{restaurantName}", method = RequestMethod.GET)
    public List<RestaurantInfo> findByRestaurantName(@PathVariable String restaurantName) {
        List<RestaurantInfo> tmp = this.restaurantService.findByRestaurantName(restaurantName);
        return tmp;
    }



}
