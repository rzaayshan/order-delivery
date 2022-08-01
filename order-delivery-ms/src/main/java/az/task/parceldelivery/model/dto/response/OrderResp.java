package az.task.parceldelivery.model.dto.response;

import az.task.parceldelivery.util.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResp {

    private Long id;
    private String name;
    private OrderStatus status;
    private String deliveryAddress;
    private String deliveryTime;
    private LocalDateTime createdDate;
}
