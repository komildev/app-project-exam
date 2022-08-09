package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfoDTO {

    private String name;

    private Integer categoryId;

    private Integer attachmentId;

    private Integer measuremntId;

}

