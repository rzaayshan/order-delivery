package az.task.parceldelivery.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqs_customers")
    @SequenceGenerator(name = "sqs_customers", sequenceName = "sqs_customers", allocationSize = 1)
    private Long id;

    private String cardNumber;

    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Builder.Default
    Set<OrderEntity> orders = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
