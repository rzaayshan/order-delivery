package az.task.parceldelivery.model.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class CreateCustomerReq {

    @ApiModelProperty(example = "1234123412341234")
    private String cardNumber;
    private String address;
    @NotNull
    private CreateUserReq userData;
}
