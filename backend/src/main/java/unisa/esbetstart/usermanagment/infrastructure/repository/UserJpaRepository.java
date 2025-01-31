package unisa.esbetstart.usermanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
