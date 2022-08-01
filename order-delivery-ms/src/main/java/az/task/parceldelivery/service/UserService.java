package az.task.parceldelivery.service;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */

@Service
public interface UserService {
    UserEntity saveUser(UserEntity user);

    List<UserEntity> getUsers();

    public UserEntity getUser(String username);
}
