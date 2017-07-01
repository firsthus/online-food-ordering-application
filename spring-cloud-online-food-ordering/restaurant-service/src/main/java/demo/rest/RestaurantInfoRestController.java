package demo.rest;

import demo.domain.RestaurantInfo;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
    public List<RestaurantInfo> uploadRestaurant(@RequestBody List<RestaurantInfo> restaurantInfos) {
        return this.restaurantService.saveRestaurantInfos(restaurantInfos);
    }


    @RequestMapping(value = "/restaurant", method = RequestMethod.DELETE)
    public void purge() {
        this.restaurantService.deleteAllRestaurants();
    }

    @RequestMapping(value = "/restaurant/{restaurantName}/list", method = RequestMethod.GET)
    public List<RestaurantInfo> findByRestaurantName(@PathVariable String restaurantName) {
        List<RestaurantInfo> tmp = this.restaurantService.findByRestaurantName(restaurantName);
        return tmp;
    }


    @RequestMapping(value = "/restaurant/{restaurantName}", method = RequestMethod.GET)
    public RestaurantInfo showRestaurantByName(@PathVariable String restaurantName, Model model){
        RestaurantInfo restaurantInfo = restaurantService.findFirstByRestaurantName(restaurantName);
        model.addAttribute("myData",restaurantInfo);
        return restaurantInfo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greetings(){
        return "Welcome to restaurant root!";
    }
}
