package az.task.parceldelivery.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourierResp {
    private Long id;
    private String name;
    private String status;
}
