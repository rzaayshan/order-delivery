package az.task.parceldelivery.service.impl;

import az.task.parceldelivery.exception.RecordNotFoundException;
import az.task.parceldelivery.mapper.CustomerMapper;
import az.task.parceldelivery.mapper.OrderMapper;
import az.task.parceldelivery.model.dto.request.CreateCustomerReq;
import az.task.parceldelivery.model.dto.request.CreateOrderReq;
import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.dto.response.UserResp;
import az.task.parceldelivery.model.entity.CustomerEntity;
import az.task.parceldelivery.repo.CustomerRepo;
import az.task.parceldelivery.repo.OrderRepo;
import az.task.parceldelivery.service.CustomerService;
import az.task.parceldelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static az.task.parceldelivery.util.enums.OrderStatus.CANCELED;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResp signupCustomer(CreateCustomerReq customerReq) {
        customerReq.getUserData().setPassword(passwordEncoder.encode(customerReq.getUserData().getPassword()));
        var user = customerRepo.save(customerMapper.dtoToEntity(customerReq));
        log.info("saved user: {}", user);
        return UserResp.builder().userId(user.getId()).build();
    }

    @Override
    public void createOrder(String userId, CreateOrderReq createOrderReq) {
        var customer = getCustomerById(Long.valueOf(userId));
        var orders = customer.getOrders();
        var orderEntity = orderMapper.dtoToEntity(createOrderReq);
        orderEntity.setCustomer(customer);
        orders.add(orderEntity);
        customerRepo.save(customer);
    }

    @Override
    public void changeDest(String username, Long orderId, UpdateOrderReq parcelReq) {
        var order = orderService.getOrderByIdAndCustomerUserId(orderId, Long.valueOf(username));
        order.setDeliveryAddress(parcelReq.getDeliveryAddress());
        orderService.saveOrder(order);
    }

    @Override
    public void cancelOrder(String userid, Long orderId) {
        var order = orderService.getOrderByIdAndCustomerUserId(orderId, Long.valueOf(userid));
        order.setStatus(CANCELED.name());
        orderService.saveOrder(order);
    }

    @Override
    public OrderResp getOrder(String username, Long orderId) {
        var order = orderService.getOrderByIdAndCustomerUserId(orderId, Long.valueOf(username));
        return orderMapper.entityToDto(order);
    }

    @Override
    public Set<OrderResp> getOrders(String userId) {
        var customer = getCustomerById(Long.valueOf(userId));
        return orderMapper.entityToDto(customer.getOrders());
    }

    private CustomerEntity getCustomerById(Long userId) {
        var customer = customerRepo.findByUserId(userId).orElseThrow(() -> {
            throw new RecordNotFoundException(String.format("customer not found by id %s", userId));
        });
        log.info("fetched customer by id {}", customer);
        return customer;
    }

}
