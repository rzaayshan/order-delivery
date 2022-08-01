package az.task.parceldelivery.mapper;

import az.task.parceldelivery.model.dto.request.CreateCustomerReq;
import az.task.parceldelivery.model.entity.CustomerEntity;
import az.task.parceldelivery.util.constant.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import static az.task.parceldelivery.util.constant.UserRole.ROLE_CUSTOMER;
import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;


@Mapper(
        componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE

)
public interface CustomerMapper {

    @Mapping(target = "user", source = "userData")
    @Mapping(target = "user.role", constant = ROLE_CUSTOMER)
    CustomerEntity dtoToEntity(CreateCustomerReq customerReq);
}
