package pdp.com.appprojectexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEditWithProductDTO {

    private String name;

    private String description;

    private List<ProductEditForCategoryDTO> products;

}
