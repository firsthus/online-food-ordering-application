package demo;


import demo.domain.ItemInfo;
import demo.domain.ItemRepository;
import demo.domain.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void whenSaveItem_expectOk(){
        final String name = "food1" ;
        final double price = 1.0;
        final String description = "This is food1";
        final String restaurantId = "12345";
        ItemInfo result = this.itemRepository.save(new ItemInfo(name, price, description, restaurantId));
        assertThat(this.itemRepository.findFirstByName("food1").getName()).isEqualTo(name);
        assertThat(this.itemRepository.findFirstByName("food1").getPrice()).isEqualTo(price);
        assertThat(this.itemRepository.findFirstByName("food1").getDescription()).isEqualTo(description);
        assertThat(this.itemRepository.findFirstByName("food1").getRestaurantId()).isEqualTo(restaurantId);

        itemRepository.deleteAll();
    }


    @Test
    public void whenFindFirstByName_expectOk(){
        final String name = "food1" ;
        this.itemRepository.save(new ItemInfo(name));
        assertThat((this.itemRepository.findFirstByName(name).getName()).equals(name));
        itemRepository.deleteAll();
    }

    @Test
    public void whenFindFirstByRestaurantId_expectOk(){
        final String name1 = "food1";
        final double price1 = 1.0;
        final String description1 = "This is food1";
        final String restaurantId1 = "12345";
        final String name2 = "food2";
        final double price2 = 2.0;
        final String description2 = "This is food2";
        final String restaurantId2 = "12345";
        ItemInfo itemInfo1 = new ItemInfo(name1, price1, description1, restaurantId1);
        ItemInfo itemInfo2 = new ItemInfo(name2, price2, description2, restaurantId2);

        List<ItemInfo> itemInfos = new ArrayList<>();
        itemInfos.add(itemInfo1);
        itemInfos.add(itemInfo2);


        this.itemRepository.deleteAll();
        List<ItemInfo> result = this.itemRepository.save(itemInfos);
        assertThat(this.itemRepository.findByRestaurantId("12345")).size().isEqualTo(2);
        assertThat(this.itemRepository.findByRestaurantId("12345").get(0).getRestaurantId()).isEqualTo(restaurantId1);
        assertThat(this.itemRepository.findByRestaurantId("12345").get(1).getRestaurantId()).isEqualTo(restaurantId1);

        itemRepository.deleteAll();
    }
}
