package az.task.parceldelivery.service;

import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.OrderEntity;
import az.task.parceldelivery.repo.OrderRepo;

import java.util.Set;

public interface OrderService {

    OrderEntity getOrderById(Long id);

    Set<OrderResp> getOrders();

    void saveOrder(OrderEntity orderEntity);

    OrderEntity getOrderByIdAndCourierUserId(Long orderId, Long userId);
    OrderEntity getOrderByIdAndCustomerUserId(Long orderId, Long userId);
}
