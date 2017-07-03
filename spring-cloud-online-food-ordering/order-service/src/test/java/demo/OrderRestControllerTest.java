package demo;

import demo.domain.OrderInfo;
import demo.domain.ItemRepository;
import demo.domain.OrderInfoDto;
import demo.domain.OrderRepository;
import demo.rest.OrderInfoRestController;
import demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OrderRestControllerTest {

    private OrderService orderService;
    private OrderRepository orderRepository;
    private OrderInfoRestController orderInfoRestController;
    private OrderInfoDto orderInfoDto;
    private List<OrderInfo> inputOrderInfos;


    @Before
    public void setupMock(){
        orderRepository = mock(OrderRepository.class);
        orderService = mock(OrderService.class);
        orderInfoRestController = new OrderInfoRestController(orderService);
        inputOrderInfos = new ArrayList<>();
        inputOrderInfos.add(generateOrderInfo("orderId1","restaurantId1", "user1","note1","name1", "address1"));
        inputOrderInfos.add(generateOrderInfo("orderId2","restaurantId1", "user1","note1","name1", "address1"));
    }

    private OrderInfo generateOrderInfo(String orderId, String restaurantId, String userId, String notes, String restaurantName, String deliveryAddress) {
        OrderInfo orderInfo  = new OrderInfo(orderId, restaurantId, userId, notes, restaurantName, deliveryAddress);
        return orderInfo;
    }


    @Test
    public void whenReceivePut_changeOrderStatus() {
        String orderId = "orderId1";
        OrderInfo.OrderStatus orderStatus = OrderInfo.OrderStatus.paid;
    }
}
