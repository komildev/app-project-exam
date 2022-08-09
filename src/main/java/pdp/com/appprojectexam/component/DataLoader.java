package pdp.com.appprojectexam.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pdp.com.appprojectexam.entity.Role;
import pdp.com.appprojectexam.entity.User;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.enums.PermissionEnum;
import pdp.com.appprojectexam.repository.RoleRepository;
import pdp.com.appprojectexam.repository.UserRepository;
import pdp.com.appprojectexam.repository.WareHouseRepository;
import pdp.com.appprojectexam.utils.AppConstant;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    private final WareHouseRepository wareHouseRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            createSuperUser();
            createUserRoler();
        }

    }

    private void createSuperUser() {
        Role adminRole = new Role(
                AppConstant.ADMIN_ROLE,
                AppConstant.ADMIN_ROLE,
                Set.of(PermissionEnum.values())
//                Arrays.asList(PermissionEnum.values())
        );
        roleRepository.save(adminRole);


        User adminUser = new User(
                "Admin",
                "Admin",
                "+998900050161",
                "ykomil110@gmail.com",
                passwordEncoder.encode("root123"),
                null,
                adminRole,
                "12345");
        adminUser.setEnabled(true);
        userRepository.save(adminUser);
    }

    private void createUserRoler() {
        Role role = new Role(
                AppConstant.USER_ROLE,
                "User role",
                //  List.of(PermissionEnum.VIEW_PRODUCT)
                Set.of(PermissionEnum.VIEW_PRODUCT)
        );
        roleRepository.save(role);
    }
}
