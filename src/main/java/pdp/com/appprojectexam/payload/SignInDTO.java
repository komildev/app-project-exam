package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO {

   // @NotNull(message = "ndsjnj")
   // @Pattern(regexp = "[0-9]{12,20}",message = "njnxj")
    private String username;

//    @NotNull(message = "ndsjnj")
//    @Pattern(regexp = "[0-9]{12,20}",message = "njnxj")
    private String password;

}
