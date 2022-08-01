package az.task.parceldelivery.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResp {
    Long userId;

    public static UserResp of(Long userId) {
        return UserResp.builder().userId(userId).build();
    }

}
