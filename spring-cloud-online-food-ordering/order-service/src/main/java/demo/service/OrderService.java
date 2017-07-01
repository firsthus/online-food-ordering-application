package demo.service;

import demo.domain.OrderInfo;
import demo.domain.OrderInfoDto;

import java.util.List;

public interface OrderService {
    List<OrderInfo> saveOrdersByRestaurantId(String resterauntId, List<OrderInfo> orderInfos);
    List<OrderInfo> saveOrders(List<OrderInfo> orderInfos);
    List<OrderInfo> viewOrders(String resterauntId);
    OrderInfo findFirstByOrderId(String orderId);
    void deleteOrder(String orderId);


    OrderInfoDto viewOrderConfirmation(String orderId);

    void updateOrderStatus(String orderId, OrderInfo.OrderStatus orderStatus);
}
