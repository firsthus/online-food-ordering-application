package demo;

import demo.domain.ItemInfo;
import demo.domain.ItemRepository;
import demo.domain.RestaurantInfo;
import demo.domain.RestaurantRepository;
import demo.rest.ItemInfoRestController;
import demo.rest.RestaurantInfoRestController;
import demo.service.ItemService;
import demo.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantRestControllerTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private RestaurantInfoRestController restaurantController;
    private List<RestaurantInfo> inputRestaurants;

    private ItemService itemService;
    private ItemRepository itemRepository;
    private ItemInfoRestController itemController;
    private List<ItemInfo> inputItems;

    @Before
    public void setupMock(){
        restaurantRepository = mock(RestaurantRepository.class);
        restaurantService = mock(RestaurantService.class);
        restaurantController = new RestaurantInfoRestController(restaurantService);
        inputRestaurants = new ArrayList<>();

        itemRepository = mock(ItemRepository.class);
        itemService = mock(ItemService.class);
        itemController = new ItemInfoRestController(restaurantService, itemService);
        inputItems = new ArrayList<>();

        inputRestaurants.add(generateRestaurantInfo("restaurant1","1 main street", "111-111-1111","12345"));
        inputRestaurants.add(generateRestaurantInfo("restaurant2","2 main street", "222-222-2222","67890"));

        inputItems.add(generateItemInfo("food1",1.0, "this is food 1", "12345"));
        inputItems.add(generateItemInfo("food2",2.0, "this is food 2", "12345"));
        inputItems.add(generateItemInfo("food3",3.0, "this is food 3", "12345"));
        inputItems.add(generateItemInfo("food4",4.0, "this is food 4", "67890"));
        inputItems.add(generateItemInfo("food5",5.0, "this is food 5", "27890"));
    }

    private ItemInfo generateItemInfo(String name, double price, String description, String restaurantId) {
        ItemInfo itemInfo = new ItemInfo(name, price, description, restaurantId);
        //itemInfo.set
        return itemInfo;
    }

    private RestaurantInfo generateRestaurantInfo(String restaurantName, String address, String phoneNumber, String restaurantId) {
        RestaurantInfo restaurantInfo = new RestaurantInfo(restaurantName, address, phoneNumber, restaurantId);
        return restaurantInfo;
    }


    @Test
    public void whenItemAndRestaurant_returnItemWithSameRestaurantId() {

        List<RestaurantInfo> restaurantInfoList = new ArrayList<>();
        restaurantInfoList.add(generateRestaurantInfo("restaurant1","1 main street", "111-111-1111","12345"));
        restaurantInfoList.add(generateRestaurantInfo("restaurant2","2 main street", "222-222-2222","67890"));

        when(restaurantService.saveRestaurantInfos(inputRestaurants)).thenReturn(restaurantInfoList);

        assertThat(restaurantController.uploadRestaurant(inputRestaurants)).size().isEqualTo(2);
        assertThat(restaurantController.uploadRestaurant(inputRestaurants).get(0)).isEqualTo(restaurantInfoList.get(0));
        assertThat(restaurantController.uploadRestaurant(inputRestaurants).get(1)).isEqualTo(restaurantInfoList.get(1));

        List<ItemInfo> itemInfoList = new ArrayList<>();
        itemInfoList.add(generateItemInfo("food1",1.0, "this is food 1", "12345"));
        itemInfoList.add(generateItemInfo("food2",2.0, "this is food 2", "12345"));
        itemInfoList.add(generateItemInfo("food3",3.0, "this is food 3", "12345"));
        itemInfoList.add(generateItemInfo("food4",4.0, "this is food 4", "67890"));
        itemInfoList.add(generateItemInfo("food5",5.0, "this is food 5", "27890"));

        //itemInfoList.add(generateItemInfo("food1",1.0, "this is food 1", "12345"));
        when(itemService.saveMenu(inputItems)).thenReturn(itemInfoList);
        assertThat(itemController.uploadMenu(inputItems)).size().isEqualTo(5);
    }
}
