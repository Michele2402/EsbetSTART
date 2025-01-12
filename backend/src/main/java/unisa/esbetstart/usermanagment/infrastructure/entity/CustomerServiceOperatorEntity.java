package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("CUSTOMER_SERVICE_OPERATOR")
public class CustomerServiceOperatorEntity extends UserEntity {
}
