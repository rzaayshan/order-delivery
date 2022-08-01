package az.task.parceldelivery.model.dto.request;

import az.task.parceldelivery.util.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateOrderReq {
    @ApiModelProperty(example = "Baku")
    private String deliveryAddress;

    @ApiModelProperty(example = "CANCEL")
    private OrderStatus status;
}
