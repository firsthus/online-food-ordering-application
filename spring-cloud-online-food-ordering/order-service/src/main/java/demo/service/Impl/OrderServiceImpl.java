package demo.service.Impl;


import demo.domain.*;
import demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
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
    public void purge(){
        orderRepository.deleteAll();
    }

    @Override
    public List<OrderInfo> viewOrders(String restaurantId){
        return orderRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public OrderInfo findFirstByOrderId(String orderId) {
        return orderRepository.findFirstByOrderId(orderId);
    }

    @Override
    public void deleteOrder(String orderId){
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public OrderInfoDto viewOrderConfirmation(String orderId) {

        OrderInfo orderInfo = orderRepository.findFirstByOrderId(orderId);
        OrderInfoDto orderInfoDto = new OrderInfoDto(orderInfo);
        if(orderInfoDto.getOrderStatus().equals(OrderInfo.OrderStatus.paid)){
            orderInfoDto.setDeliveryTime(new Random().nextInt(55)+5);
        }
        log.info("using orderInfoDto to view order for Id:" + orderId + " deliverytime:" + orderInfoDto.getDeliveryTime());
        List<Item> itemList = itemRepository.findByOrderInfo(orderInfo);
        orderInfoDto.setItemList(itemList);
        double totPrice = 0.0;
        for(Item item: itemList ) {
            totPrice += item.getPrice() * item.getQuantity();
        }
        orderInfoDto.setPrice(totPrice);
        return orderInfoDto;
    }

    @Override
    public void updateOrderStatus(String orderId, OrderInfo.OrderStatus orderStatus){
	System.out.println("order service handling update OrderStatus to" + orderStatus + "of orderId: " + orderId);
        OrderInfo orderInfo = findFirstByOrderId(orderId);
        orderInfo.setOrderStatus(orderStatus);
        orderRepository.save(orderInfo);
    }
}
