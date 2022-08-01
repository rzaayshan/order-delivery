package az.task.parceldelivery.service.impl;

import az.task.parceldelivery.mapper.CourierMapper;
import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.response.CourierResp;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.dto.response.UserResp;
import az.task.parceldelivery.repo.CourierRepo;
import az.task.parceldelivery.service.AdminService;
import az.task.parceldelivery.service.CourierService;
import az.task.parceldelivery.service.OrderService;
import az.task.parceldelivery.service.UserService;
import az.task.parceldelivery.util.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;
    private final CourierService courierService;

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus status) {
        var order = orderService.getOrderById(orderId);
        order.setStatus(status.name());
        orderService.saveOrder(order);
    }

    @Override
    public Set<OrderResp> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public void assignOrder(Long orderId, Long courierId) {
        var order = orderService.getOrderById(orderId);
        var courier = courierService.getCourierById(courierId);
        order.setCourier(courier);
        orderService.saveOrder(order);
    }

    @Override
    public UserResp createCourier(CreateCourierReq courierReq) {
        courierReq.getUserData().setPassword(passwordEncoder.encode(courierReq.getUserData().getPassword()));
        var courier  = courierService.createCourier(courierReq);
        return UserResp.of(courier.getId());
    }

    @Override
    public Set<CourierResp> getCouriers() {
        return courierService.getCouriers();
    }
}
