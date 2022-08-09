package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InWarehouseInfoDTO {

    private Integer wareHouseId;

    private Integer supplierId;

    private Integer currencyId;

    private Integer factureNumber;

}
