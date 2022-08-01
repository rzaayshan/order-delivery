package az.task.parceldelivery.model.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserReq {
    @NotNull
    @Size(min=8)
    @ApiModelProperty(example = "Ayshan Rzayeva")
    private String name;

    @NotNull
    @Email
    @ApiModelProperty(example = "rza.ayshan@gmail.com")
    private String username;

    @NotNull
    @Size(min=8)
    @ApiModelProperty(example = "123456789")
    private String password;
}
