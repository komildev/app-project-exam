package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAddDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer roleId;
    private Integer wareHouseId;
}
