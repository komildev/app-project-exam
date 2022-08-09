package pdp.com.appprojectexam.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierAddDTO {

    private String name;

    private String phoneNumber;
}
