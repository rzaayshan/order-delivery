package az.task.parceldelivery.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "COURIERS")
public class CourierEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqs_couriers")
    @SequenceGenerator(name = "sqs_couriers", sequenceName = "sqs_couriers", allocationSize = 1)
    private Long id;

    private String vehicle;

    private String status;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    @Builder.Default
    Set<OrderEntity> orders = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
