package az.task.parceldelivery.model.dto.request;

import az.task.parceldelivery.util.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrderReq {

    @NotNull
    @ApiModelProperty(example = "parcel1")
    private String name;

    @NotNull
    @ApiModelProperty(example = "PROCESSING")
    private OrderStatus status;

    @NotNull
    @ApiModelProperty(example = "Baku")
    private String deliveryAddress;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2022-09-09")
    private LocalDateTime deliveryDate;
}
