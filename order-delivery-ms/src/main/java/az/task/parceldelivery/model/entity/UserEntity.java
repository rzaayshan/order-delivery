package az.task.parceldelivery.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqs_users")
    @SequenceGenerator(name = "sqs_users", sequenceName = "sqs_users", allocationSize = 1)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String role;

    public String[] getRoles() {
        return role == null || role.isEmpty() ? new String[]{} : role.split(":");
    }

    @OneToOne(mappedBy = "user")
    private CustomerEntity customer;

    @OneToOne(mappedBy = "user")
    private CourierEntity courier;
}
