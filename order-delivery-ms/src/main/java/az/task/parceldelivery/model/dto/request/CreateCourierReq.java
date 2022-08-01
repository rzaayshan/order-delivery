package az.task.parceldelivery.model.dto.request;


import az.task.parceldelivery.util.enums.CourierStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@Builder
public class CreateCourierReq {

    @ApiModelProperty(example = "tesla")
    private String vehicle;
    @ApiModelProperty(example = "BUSY")
    private CourierStatus status;
    @NotNull
    private CreateUserReq userData;
}
