package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeProductAddDTO {

    private Integer productId;

    private Integer measurementId;

    private Integer incomeWarehouseId;

    private Double measurementAmount;

    private Double price;
    private Timestamp expirationDate;

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = Timestamp.valueOf(expirationDate);
    }
}
