package az.task.parceldelivery.controller;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.shared.RestResponse;
import az.task.parceldelivery.service.AdminService;
import az.task.parceldelivery.util.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static az.task.parceldelivery.util.constant.UserRole.ROLE_ADMIN;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@RolesAllowed(ROLE_ADMIN)
public class AdminController {
    private final AdminService adminService;

    @PutMapping(path = "/orders/{order-id}/status")
    public ResponseEntity<RestResponse<Object>> changeOrderStatus(@PathVariable(name = "order-id") Long orderId,
                                                                  @RequestParam OrderStatus status) {
        adminService.changeOrderStatus(orderId, status);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<RestResponse<Object>> getOrders() {
        return ResponseEntity.ok(RestResponse.of(adminService.getOrders()));
    }

    @PutMapping(path = "/orders/{order-id}/{courier-id}")
    public ResponseEntity<RestResponse<Object>> assignOrder(@PathVariable(name = "order-id") Long orderId,
                                                            @PathVariable(name = "courier-id") Long courierId) {
        adminService.assignOrder(orderId, courierId);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

    @PostMapping(path = "/couriers")
    public ResponseEntity<RestResponse<Object>> createCourier(@RequestBody CreateCourierReq courierReq) {
        return ResponseEntity.ok(RestResponse.of(adminService.createCourier(courierReq)));
    }

    @GetMapping(path = "/couriers")
    public ResponseEntity<RestResponse<Object>> getCouriers() {
        return ResponseEntity.ok(RestResponse.of(adminService.getCouriers()));
    }
}
