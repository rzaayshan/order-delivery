package az.task.parceldelivery.service.impl;

import az.task.parceldelivery.exception.RecordNotFoundException;
import az.task.parceldelivery.mapper.OrderMapper;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.OrderEntity;
import az.task.parceldelivery.repo.OrderRepo;
import az.task.parceldelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @Override
    public OrderEntity getOrderById(Long id) {
        var order = orderRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("order not found by id %s", id)));
        log.info("fetched order by id: {}", order);
        return order;
    }

    @Override
    public Set<OrderResp> getOrders() {
        var orders = orderMapper.entityToDto(orderRepo.findAll());
        log.info("fetched orders: {}", orders);
        return orders;
    }

    @Override
    public void saveOrder(OrderEntity order) {
        var saved = orderRepo.save(order);
        log.info("order saved: {}", saved);
    }

    @Override
    public OrderEntity getOrderByIdAndCourierUserId(Long orderId, Long userId) {
        var order = orderRepo.findByIdAndCourierUserId(orderId, userId).orElseThrow(() -> {
            throw new RecordNotFoundException(String.format("order not found by id %s and courier user id %s", orderId, userId));
        });
        log.info("fetched order by id and courier user id: {}", order);
        return order;
    }

    @Override
    public OrderEntity getOrderByIdAndCustomerUserId(Long orderId, Long userId) {
        var order = orderRepo.findByIdAndCustomerUserId(orderId, userId).orElseThrow(() -> {
            throw new RecordNotFoundException(String.format("order not found by id %s and customer user id %s", orderId, userId));
        });
        log.info("fetched order by id and courier user id: {}", order);
        return order;
    }
}
