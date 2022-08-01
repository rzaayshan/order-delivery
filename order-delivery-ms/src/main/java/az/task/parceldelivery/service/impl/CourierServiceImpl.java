package az.task.parceldelivery.service.impl;

import az.task.parceldelivery.exception.RecordNotFoundException;
import az.task.parceldelivery.mapper.CourierMapper;
import az.task.parceldelivery.mapper.OrderMapper;
import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.dto.response.CourierResp;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.CourierEntity;
import az.task.parceldelivery.repo.CourierRepo;
import az.task.parceldelivery.repo.OrderRepo;
import az.task.parceldelivery.service.CourierService;
import az.task.parceldelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourierServiceImpl implements CourierService {

    private final CourierRepo courierRepo;
    private final CourierMapper courierMapper;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    public Set<OrderResp> getOrders(String username) {
        var courier = getCourierById(Long.valueOf(username));
        return orderMapper.entityToDto(courier.getOrders());
    }

    @Override
    public OrderResp getOrder(String username, Long orderId) {
        var order = orderService.getOrderByIdAndCourierUserId(orderId, Long.valueOf(username));
        return orderMapper.entityToDto(order);
    }

    @Override
    public void changeOrderStatus(String username, Long orderId, UpdateOrderReq orderReq) {
        var order = orderService.getOrderByIdAndCourierUserId(orderId, Long.valueOf(username));
        order.setStatus(orderReq.getStatus().name());
        orderService.saveOrder(order);
    }

    @Override
    public CourierEntity getCourierById(Long id) {
        var courier = courierRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("courier not found by id %s", id)));
        log.info("fetched courier by id: {}", courier);
        return courier;
    }

    @Override
    public Set<CourierResp> getCouriers() {
        var couriers = courierRepo.findAll();
        log.info("fetched couriers: {}", couriers);
        return courierMapper.entityToDto(couriers);
    }

    @Override
    public CourierEntity createCourier(CreateCourierReq courierReq) {
        var saved = courierRepo.save(courierMapper.dtoToEntity(courierReq));
        log.info("saved courier: {}", saved);
        return saved;
    }
}
