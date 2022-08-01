package az.task.parceldelivery.service;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.dto.response.CourierResp;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.CourierEntity;

import java.util.Set;

public interface CourierService {
    Set<OrderResp> getOrders(String username);

    OrderResp getOrder(String username, Long orderId);

    void changeOrderStatus(String username, Long orderId, UpdateOrderReq orderReq);

    CourierEntity getCourierById(Long id);

    Set<CourierResp> getCouriers();

    CourierEntity createCourier(CreateCourierReq createCourierReq);
}
