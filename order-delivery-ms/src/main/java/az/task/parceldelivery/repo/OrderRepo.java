package az.task.parceldelivery.repo;

import az.task.parceldelivery.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndCustomerUserId(Long id, Long clientUserId);

    Optional<OrderEntity> findByIdAndCourierUserId(Long id, Long courierUserId);
}
