package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeProductUpdateDTO {

    private Integer productId;

    private Integer measurementId;

    private Double measurementAmount;

    private Double price;

    private Timestamp expirationDate;
}
