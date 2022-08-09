package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdAndStatusTrueAndEnabledTrue(String phoneNumber, Integer id);

    boolean existsByPhoneNumber(String phoneNumber);
}
