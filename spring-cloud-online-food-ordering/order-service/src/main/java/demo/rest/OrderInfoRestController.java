package demo.rest;

import demo.domain.OrderInfo;
import demo.domain.OrderInfoDto;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderInfoRestController {

    private OrderService orderService;

    @Autowired
    public OrderInfoRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/restaurant/{restaurantId}/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderInfo> saveOrdersByRestaurantId(@PathVariable String restaurantId, @RequestBody List<OrderInfo> orderInfos) {
        return orderService.saveOrdersByRestaurantId(restaurantId, orderInfos);
    }

    @RequestMapping(value = "/restaurant/order", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.CREATED)
    public void purge() {
        orderService.purge();
    }

    @RequestMapping(value = "/restaurant/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderInfo> saveOrders(@RequestBody List<OrderInfo> orderInfos) {
        return orderService.saveOrders(orderInfos);
    }

    @RequestMapping(value = "/restaurant/order", method = RequestMethod.GET)
    public String orderGreetings() {
        return "Welcome to order root page!";
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/order", method = RequestMethod.GET)
    public List<OrderInfo> viewOrders(@PathVariable String restaurantId) {
        return orderService.viewOrders(restaurantId);
    }

    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.GET)
    public OrderInfoDto viewOrderConfirmation(@PathVariable String orderId) {
        // first check current order status
        OrderInfoDto orderInfoDto = orderService.viewOrderConfirmation(orderId);
        return orderInfoDto;
    }

    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.PUT)
    public void updateOrderStatus(@PathVariable String orderId,@RequestBody OrderInfo orderInfo){
        OrderInfo.OrderStatus orderStatus = orderInfo.getOrderStatus();
        OrderInfo orderInfo1 = orderService.findFirstByOrderId(orderId);
        //System.out.println("order service handling put request on path:/restaurant/order/" + orderId +
        //        ", original status is:" + orderInfo1.getOrderStatus() +
         //       ", target orderstatus is"+ orderStatus);
        orderService.updateOrderStatus(orderId, orderStatus);
        System.out.println("new OrderInfo status is:" + orderInfo1.getOrderStatus());
    }


    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }

}
