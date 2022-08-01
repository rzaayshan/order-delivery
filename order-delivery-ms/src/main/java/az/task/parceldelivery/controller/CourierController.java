package az.task.parceldelivery.controller;

import az.task.parceldelivery.model.dto.request.UpdateOrderReq;
import az.task.parceldelivery.model.shared.RestResponse;
import az.task.parceldelivery.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static az.task.parceldelivery.util.constant.UserRole.ROLE_COURIER;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/courier")
@RolesAllowed(ROLE_COURIER)
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/{order-id}")
    public ResponseEntity<RestResponse<Object>> getOrder(@AuthenticationPrincipal String username,
                                                         @PathVariable Long orderId) {
        return ResponseEntity.ok(RestResponse.of(courierService.getOrder(username, orderId)));
    }

    @GetMapping("/")
    public ResponseEntity<RestResponse<Object>> getOrders(@AuthenticationPrincipal String username) {
        return ResponseEntity.ok(RestResponse.of(courierService.getOrders(username)));
    }

    @PutMapping("/{order-id}")
    public ResponseEntity<RestResponse<Object>> changeOrderStatus(@AuthenticationPrincipal String username,
                                                                  @PathVariable Long orderId,
                                                                  @RequestBody UpdateOrderReq orderReq) {
        courierService.changeOrderStatus(username, orderId, orderReq);
        return ResponseEntity.ok(RestResponse.of(Optional.empty()));
    }

}
