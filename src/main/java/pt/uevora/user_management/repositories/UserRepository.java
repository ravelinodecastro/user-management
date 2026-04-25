package pt.uevora.user_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.uevora.user_management.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
