package ewkconsulting.software.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ewkconsulting.software.security.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	boolean existsUserByUsername(String username);
	void deleteByUsername(String username);
}
