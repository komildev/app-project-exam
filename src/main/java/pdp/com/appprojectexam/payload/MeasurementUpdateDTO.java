package pdp.com.appprojectexam.payload;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class MeasurementUpdateDTO {

    private String name;

    private boolean status;

}
