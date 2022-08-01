package az.task.parceldelivery.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {

    private HttpStatus status;
    private T data;

    public static <T> RestResponse<T> of(T data) {
        return new RestResponse<>(HttpStatus.OK, data);
    }

}
