package demo.service.Impl;


import demo.domain.OrderInfo;
import demo.domain.OrderRepository;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderInfo> saveOrdersByRestaurantId(String restaurantId, List<OrderInfo> orderInfos){
        for(OrderInfo orderInfo: orderInfos){
            orderInfo.setRestaurantId(restaurantId);
        }
        return orderRepository.save(orderInfos);
    }

    @Override
    public List<OrderInfo> saveOrders(List<OrderInfo> orderInfos) {
        return orderRepository.save(orderInfos);
    }


    @Override
    public List<OrderInfo> viewOrders(String restaurantId){
        return orderRepository.findByRestaurantId(restaurantId);
    }


    @Override
    public void deleteOrder(String orderId){
        orderRepository.deleteByOrderId(orderId);
    }
}
