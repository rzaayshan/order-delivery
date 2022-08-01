package az.task.parceldelivery.mapper;

import az.task.parceldelivery.model.dto.request.CreateCourierReq;
import az.task.parceldelivery.model.dto.response.CourierResp;
import az.task.parceldelivery.model.entity.CourierEntity;
import az.task.parceldelivery.util.constant.UserRole;
import org.mapstruct.*;

import java.util.Collection;
import java.util.Set;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(
        componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourierMapper {

    @Mapping(target = "user", source = "userData")
    @Mapping(target = "user.role", constant = UserRole.ROLE_COURIER)
    CourierEntity dtoToEntity(CreateCourierReq courierReq);

    Set<CourierResp> entityToDto(Collection<CourierEntity> courierEntity);
}
