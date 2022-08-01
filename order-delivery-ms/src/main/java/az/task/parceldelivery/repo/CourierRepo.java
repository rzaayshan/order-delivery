package az.task.parceldelivery.repo;

import az.task.parceldelivery.model.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepo extends JpaRepository<CourierEntity, Long> {
}
