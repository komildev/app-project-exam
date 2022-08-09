package pdp.com.appprojectexam.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeasurementAddDTO {

    private String name;

    private boolean status=true;

}
