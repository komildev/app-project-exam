package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutcomeProductAddDTO {

    private Integer outcomeWarehouseId;

    private Integer productId;

    private Integer measurementId;

    private Double measurementAmount;

    private Double price;


}
