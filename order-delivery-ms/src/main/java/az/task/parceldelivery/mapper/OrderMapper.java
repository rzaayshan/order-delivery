package az.task.parceldelivery.mapper;

import az.task.parceldelivery.model.dto.request.CreateOrderReq;
import az.task.parceldelivery.model.dto.response.OrderResp;
import az.task.parceldelivery.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.Set;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(
        componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderEntity dtoToEntity(CreateOrderReq parcelReq);

    OrderResp entityToDto(OrderEntity orderEntity);


    Set<OrderResp> entityToDto(Collection<OrderEntity> orderEntity);
}
