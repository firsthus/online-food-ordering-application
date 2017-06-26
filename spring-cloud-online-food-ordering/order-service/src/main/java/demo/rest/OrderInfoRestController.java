package demo.rest;

import demo.domain.OrderInfo;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/restaurant/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderInfo> saveOrders(@RequestBody List<OrderInfo> orderInfos) {
        return orderService.saveOrders(orderInfos);
    }


    @RequestMapping(value = "/restaurant/{restaurantId}/order", method = RequestMethod.GET)
    public List<OrderInfo> viewOrders(@PathVariable String restaurantId) {
        return orderService.viewOrders(restaurantId);
    }




    @RequestMapping(value = "/restaurant/order/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }

}
