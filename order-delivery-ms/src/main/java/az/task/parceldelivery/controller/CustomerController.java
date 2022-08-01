package az.task.parceldelivery.controller;

import az.task.parceldelivery.model.dto.request.CreateCustomerReq;
import az.task.parceldelivery.model.dto.request.CreateOrderReq;
import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.shared.RestResponse;
import az.task.parceldelivery.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static az.task.parceldelivery.util.constant.UserRole.ROLE_CUSTOMER;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/sign-up")
    public ResponseEntity<RestResponse<Object>> signupCustomer(@RequestBody CreateCustomerReq customerReq) {

        return ResponseEntity.ok(RestResponse.of(customerService.signupCustomer(customerReq)));
    }

    @RolesAllowed(ROLE_CUSTOMER)
    @PostMapping("/orders/{client-id}")
    public ResponseEntity<RestResponse<Object>> createOrder(@RequestBody CreateOrderReq createOrderReq,
                                                            @AuthenticationPrincipal String username) {
        customerService.createOrder(username, createOrderReq);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

    @RolesAllowed(ROLE_CUSTOMER)
    @PutMapping("/orders/{order-id}")
    public ResponseEntity<RestResponse<Object>> changeDest(@AuthenticationPrincipal String username,
                                                           @PathVariable(name = "order-id") Long orderId,
                                                           @RequestBody UpdateOrderReq parcelReq) {
        customerService.changeDest(username, orderId, parcelReq);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

    @RolesAllowed(ROLE_CUSTOMER)
    @PutMapping("/orders/{order-id}/cancellation")
    public ResponseEntity<RestResponse<Object>> cancelOrder(@AuthenticationPrincipal String username,
                                                            @PathVariable(name = "order-id") Long orderId) {
        customerService.cancelOrder(username, orderId);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

    @RolesAllowed(ROLE_CUSTOMER)
    @GetMapping("/orders/{order-id}")
    public ResponseEntity<RestResponse<Object>> getOrder(@AuthenticationPrincipal String username,
                                                         @PathVariable(name = "order-id") Long orderId) {
        return ResponseEntity.ok(RestResponse.of(customerService.getOrder(username, orderId)));
    }

    @RolesAllowed(ROLE_CUSTOMER)
    @GetMapping("/orders")
    public ResponseEntity<RestResponse<Object>> getOrders(@AuthenticationPrincipal String username) {
        return ResponseEntity.ok(RestResponse.of(customerService.getOrders(username)));
    }
}
