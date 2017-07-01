package demo;


import demo.domain.RestaurantInfo;
import demo.domain.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void whenSaveRestaurant_expectOk(){
        final String name = "restaurant1" ;
        final String address = "address1";
        final String phone = "111-111-1111";
        RestaurantInfo result = this.restaurantRepository.save(new RestaurantInfo(name, address, phone));
        assertThat(this.restaurantRepository.findFirstByRestaurantName("restaurant1").getRestaurantName()).isEqualTo(name);
        assertThat(this.restaurantRepository.findFirstByRestaurantName("restaurant1").getAddress()).isEqualTo(address);
        assertThat(this.restaurantRepository.findFirstByRestaurantName("restaurant1").getPhoneNumber()).isEqualTo(phone);
        restaurantRepository.deleteAll();
    }

}
