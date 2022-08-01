package az.task.parceldelivery.repo;

import az.task.parceldelivery.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUserId(Long userId);
}
