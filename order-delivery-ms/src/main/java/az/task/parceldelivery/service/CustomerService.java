package az.task.parceldelivery.service;

import az.task.parceldelivery.model.dto.request.CreateCustomerReq;
import az.task.parceldelivery.model.dto.request.CreateOrderReq;
import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.dto.response.UserResp;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CustomerService {

    UserResp signupCustomer(CreateCustomerReq customerReq);
    void createOrder(String username, CreateOrderReq createOrderReq);

    void changeDest(String username, Long orderId, UpdateOrderReq parcelReq);

    void cancelOrder(String username, Long orderId);

    OrderResp getOrder(String username, Long orderId);

    Set<OrderResp> getOrders(String username);
}
