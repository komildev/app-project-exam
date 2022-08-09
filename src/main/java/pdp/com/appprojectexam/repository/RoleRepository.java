package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pdp.com.appprojectexam.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
    boolean existsByNameAndDeletedFalse(String name);
    boolean existsByNameAndIdAndDeletedFalse(String name, Integer id);

}
