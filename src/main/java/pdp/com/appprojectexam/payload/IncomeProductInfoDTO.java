package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeProductInfoDTO {

    private Integer productId;

    private Integer measurementId;

    private Integer incomeWarehouseId;

    private Double measurementAmount;

    private Double price;

    private Double finalPrice;

    private Timestamp expirationDate;
}
