package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.com.appprojectexam.enums.PermissionEnum;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleAddDTO {

    private String name;
    private String description;

    private Set<PermissionEnum> permissionEnums;
}
