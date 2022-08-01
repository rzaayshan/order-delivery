package az.task.parceldelivery.service;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.response.CourierResp;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.dto.response.UserResp;
import az.task.parceldelivery.util.enums.OrderStatus;

import java.util.Set;

public interface AdminService {
    void changeOrderStatus(Long orderId, OrderStatus status);

    Set<OrderResp> getOrders();

    void assignOrder(Long orderId, Long courierId);

    UserResp createCourier(CreateCourierReq courierReq);

    Set<CourierResp> getCouriers();
}
