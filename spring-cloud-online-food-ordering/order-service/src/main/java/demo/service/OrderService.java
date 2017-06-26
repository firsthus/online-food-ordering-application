package demo.service;

import demo.domain.OrderInfo;

import java.util.List;

public interface OrderService {
    List<OrderInfo> saveOrdersByRestaurantId(String resterauntId, List<OrderInfo> orderInfos);
    List<OrderInfo> saveOrders(List<OrderInfo> orderInfos);
    List<OrderInfo> viewOrders(String resterauntId);
    void deleteOrder(String orderId);
}
