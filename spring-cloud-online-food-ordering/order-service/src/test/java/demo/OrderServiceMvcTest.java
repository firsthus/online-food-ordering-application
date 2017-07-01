package demo;

import demo.domain.*;
import demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static demo.domain.OrderInfo.OrderStatus;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class OrderServiceMvcTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;

    private final String myParseType = "application/json;charset=UTF-8";

    private MockMvc mockMvc;

    private List<OrderInfo> inputOrders = new ArrayList<>();
    private List<Item> inputItems = new ArrayList<>();
    String testOrderId = "orderId1";

    @Before  //run this before everytime test starts
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        OrderInfo orderInfo1 = new OrderInfo("orderId1","restaurantId1","userId1","note1","res_name1","delivery_address1");
        OrderInfo orderInfo2 = new OrderInfo("orderId2","restaurantId2","userId2","note2","res_name2","delivery_address2");
        inputOrders.add(orderInfo1);
        inputOrders.add(orderInfo2);

        Item item1 = new Item("foodId1","foodName1",1.0,"description1",1,"restaurantId1", orderInfo1);
        Item item2 = new Item("foodId2","foodName2",2.0,"description2",2,"restaurantId2", orderInfo1);
        inputItems.add(item1);
        inputItems.add(item2);
    }

    @Test
    public void saveOrdersTest() throws Exception {

        List<OrderInfo> result = this.orderService.saveOrders(inputOrders);
        assertThat(result.size()).isEqualTo(2);
        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }

    @Test
    public void findByOrderIdTest() throws Exception {
        this.orderService.saveOrders(inputOrders);
        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderId()).isEqualTo("orderId1");
        assertThat(this.orderService.findFirstByOrderId("orderId3")).isEqualTo(null);
        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }


    @Test
    public void changeOrderStatusTest() throws Exception {
        this.orderService.saveOrders(inputOrders);
        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderStatus()).isEqualTo(OrderStatus.processing);


        this.orderService.updateOrderStatus("orderId1", OrderStatus.paid);

        assertThat(this.orderRepository.findAll().size()).isEqualTo(2);

        assertThat(this.orderService.findFirstByOrderId("orderId1").getOrderStatus()).isEqualTo(OrderStatus.paid);
        assertThat(this.orderService.findFirstByOrderId("orderId2").getOrderStatus()).isEqualTo(OrderStatus.processing);

        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();

    }

    @Test
    public void viewOrderConfirmationTest() throws Exception {

        List<OrderInfo> saveOrderResult = this.orderRepository.save(inputOrders);
        List<Item> saveItemResult = this.itemRepository.save(inputItems);

        OrderInfoDto orderInfoDto = this.orderService.viewOrderConfirmation(testOrderId);
        assertThat(orderInfoDto.getOrderId()).isEqualTo(testOrderId);
        assertThat(orderInfoDto.getUserId()).isEqualTo("userId1");
        assertThat(orderInfoDto.getDeliveryAddress()).isEqualTo("delivery_address1");
        assertThat(orderInfoDto.getNotes()).isEqualTo("note1");
        assertThat(orderInfoDto.getRestaurantName()).isEqualTo("res_name1");
        assertThat(orderInfoDto.getOrderStatus()).isEqualTo(OrderStatus.processing);
        assertThat(orderInfoDto.getDeliveryTime()).isEqualTo(0);

        assertThat(orderInfoDto.getItemList().size()).isEqualTo(2);
        assertThat(orderInfoDto.getItemList().get(0).getPrice()).isEqualTo(1.0);
        assertThat(orderInfoDto.getItemList().get(1).getPrice()).isEqualTo(2.0);
        assertThat(orderInfoDto.getItemList().get(0).getQuantity()).isEqualTo(1);
        assertThat(orderInfoDto.getItemList().get(1).getQuantity()).isEqualTo(2);
        assertThat(orderInfoDto.getPrice()).isEqualTo(5.0);

        this.orderService.updateOrderStatus(testOrderId, OrderStatus.paid);

        OrderInfoDto orderInfoDto1 = this.orderService.viewOrderConfirmation(testOrderId);
        assertThat(orderInfoDto1.getOrderId()).isEqualTo(testOrderId);
        assertThat(orderInfoDto1.getUserId()).isEqualTo("userId1");
        assertThat(orderInfoDto1.getDeliveryAddress()).isEqualTo("delivery_address1");
        assertThat(orderInfoDto1.getNotes()).isEqualTo("note1");
        assertThat(orderInfoDto1.getRestaurantName()).isEqualTo("res_name1");
        assertThat(orderInfoDto1.getOrderStatus()).isEqualTo(OrderStatus.paid);
        assertThat(orderInfoDto1.getDeliveryTime()).isBetween(5,60);

        this.mockMvc.perform(get("/restaurant/order/").accept(MediaType.parseMediaType(myParseType)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.parseMediaType(myParseType)));


        this.orderRepository.deleteAll();
        this.itemRepository.deleteAll();
    }

    @Test
    public void rootTest() throws Exception {

        this.mockMvc.perform(get("/restaurant/order/").accept(MediaType.parseMediaType(myParseType)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(myParseType)))
                .andExpect(content().string("Welcome to order root page!"));

    }
    /*
        @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.PUT)
    //@ResponseStatus(HttpStatus.CREATED)
    public OrderInfo changeOrderStatus(@PathVariable String orderId, @ModelAttribute OrderStatus orderStatus){
        return orderService.changeOrderStatus(orderId, orderStatus);
    }


    @Override
    public OrderInfoDto viewOrderConfirmation(String orderId) {
        OrderInfo orderInfo = findFirstByOrderId(orderId);
        OrderInfoDto orderInfoDto = new OrderInfoDto(orderInfo);
        //OrderInfoDto orderInfoDto = new OrderInfoDto();
        if(orderInfo.getOrderStatus() == OrderStatus.paid){
            orderInfoDto.setDeliveryTime(new Random().nextInt(55)+5);
        }
        return orderInfoDto;
    }
    */
}