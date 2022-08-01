package az.task.parceldelivery.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqs_orders")
    @SequenceGenerator(name = "sqs_orders", sequenceName = "sqs_orders", allocationSize = 1)
    private Long id;

    private String name;

    private String status;

    private String deliveryAddress;

    private LocalDateTime deliveryDate;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "customer_user_id", referencedColumnName = "user_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "courier_user_id", referencedColumnName = "user_id")
    private CourierEntity courier;
}
