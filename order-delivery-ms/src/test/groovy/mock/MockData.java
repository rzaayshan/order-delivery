package mock;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.request.CreateUserReq;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.CourierEntity;
import az.task.parceldelivery.model.entity.OrderEntity;
import az.task.parceldelivery.util.enums.CourierStatus;
import az.task.parceldelivery.util.enums.OrderStatus;

import java.util.Set;

public class MockData {

    public static OrderEntity getOrderEntity() {
        return OrderEntity.builder().status(OrderStatus.CANCELED.name()).courier(getCourierEntity()).build();
    }

    public static CourierEntity getCourierEntity() {
        return CourierEntity.builder().status(CourierStatus.BUSY.name()).build();
    }

    public static CreateCourierReq getCourierReq() {
        return CreateCourierReq.builder().status(CourierStatus.BUSY).userData(CreateUserReq.builder().build()).build();
    }

    public static Set<OrderResp> getOrderResp() {
        return Set.of(OrderResp.builder().build());
    }


}
