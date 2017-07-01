package demo;

import demo.domain.*;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
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
@SpringApplicationConfiguration(classes = OrderServiceApplication.class)
public class RepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    List<OrderInfo> inputOrderInfos;
    List<Item> inputItems;

    @Before
    public void setup() {
        inputOrderInfos = new ArrayList<>();
        OrderInfo orderInfo1 = new OrderInfo("orderId1", "restaurantId1", "userId1", "note1","res_name1","delivery_address1");
        OrderInfo orderInfo2 = new OrderInfo("orderId2", "restaurantId2", "userId1", "note2","res_name2","delivery_address1");
        inputOrderInfos.add(orderInfo1);
        inputOrderInfos.add(orderInfo2);

        inputItems = new ArrayList<>();
        inputItems.add(new Item("foodId1","foodName1",1.0,"description1",1,"res_Id1",orderInfo1));
        inputItems.add(new Item("foodId2","foodName2",2.0,"description2",2,"res_Id1",null));
        inputItems.add(new Item("foodId3","foodName3",3.0,"description3",3,"res_Id1",orderInfo1));
        inputItems.add(new Item("foodId4","foodName4",4.0,"description4",4,"res_Id1",orderInfo2));
    }

    @Test
    public void whenSaveOrder_expectOk() {

        List<OrderInfo> result = this.orderRepository.save(inputOrderInfos);
        List<Item> resultItems = this.itemRepository.save(inputItems);

        assertThat(this.orderRepository.count()).isEqualTo(2);
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getOrderId()).isEqualTo("orderId2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getRestaurantId()).isEqualTo("restaurantId2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getUserId()).isEqualTo("userId1");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getNotes()).isEqualTo("note2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getRestaurantName()).isEqualTo("res_name2");
        assertThat(this.orderRepository.findFirstByOrderId("orderId2").getDeliveryAddress()).isEqualTo("delivery_address1");

        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }

    @Test
    public void whenSaveItem_expectOk() {

        List<OrderInfo> resultOrders = this.orderRepository.save(inputOrderInfos);
        List<Item> resultItems = this.itemRepository.save(inputItems);

        assertThat(this.itemRepository.count()).isEqualTo(4);
        assertThat(this.itemRepository.findAll().get(0).getFoodId()).isEqualTo("foodId1");
        assertThat(this.itemRepository.findAll().get(0).getName()).isEqualTo("foodName1");
        assertThat(this.itemRepository.findAll().get(0).getPrice()).isEqualTo(1.0);
        assertThat(this.itemRepository.findAll().get(0).getDescription()).isEqualTo("description1");
        assertThat(this.itemRepository.findAll().get(0).getQuantity()).isEqualTo(1);
        assertThat(this.itemRepository.findAll().get(0).getRestaurantId()).isEqualTo("res_Id1");
        assertThat(this.itemRepository.findAll().get(0).getOrderInfo().getOrderId()).isEqualTo(inputOrderInfos.get(0).getOrderId());
        assertThat(this.orderRepository.findAll().get(0).getFoodList().size()).isEqualTo(2);
        assertThat(this.orderRepository.findAll().get(1).getFoodList().size()).isEqualTo(1);

        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }

    @Test
    public void whenFindByOrderId_expectOk(){

        List<OrderInfo> resultOrders = this.orderRepository.save(inputOrderInfos);
        List<Item> resultItems = this.itemRepository.save(inputItems);

        OrderInfo orderInfo = this.orderRepository.findFirstByOrderId("orderId1");
        assertThat(orderInfo.getOrderId()).isEqualTo("orderId1");
        assertThat(orderInfo.getFoodList().size()).isEqualTo(2);
        assertThat(orderInfo.getFoodList().get(0).getOrderInfo()).isEqualTo(orderInfo);
        assertThat(orderInfo.getFoodList().get(1).getOrderInfo()).isEqualTo(orderInfo);

        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }

}