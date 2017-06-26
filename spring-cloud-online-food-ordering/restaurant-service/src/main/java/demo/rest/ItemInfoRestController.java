package demo.rest;

import demo.domain.ItemInfo;
import demo.service.ItemService;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemInfoRestController {

    private RestaurantService restaurantService;
    private ItemService itemService;

    @Autowired
    public ItemInfoRestController(RestaurantService restaurantService, ItemService itemService) {
        this.restaurantService = restaurantService;
        this.itemService = itemService;
    }



    @RequestMapping(value = "/restaurant/menu", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<ItemInfo> uploadMenuByRestaurantId(@RequestBody List<ItemInfo> itemList) {
        return this.itemService.saveMenu(itemList);
    }



    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    public List<ItemInfo> showMenuByRestaurantId(@PathVariable String restaurantId) {
        return this.itemService.showMenu(restaurantId);
    }

    @RequestMapping(value = "/restaurant/menu", method = RequestMethod.DELETE)
    public void purge() {
        this.itemService.deleteall();
    }



}
