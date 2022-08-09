package pdp.com.appprojectexam.entity;

import lombok.*;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity(name = "income_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IncomeProduct extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private IncomeWarehouse incomeWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Measurement measurement;

    private Double measurementAmount;

    private Double price;

    private Double finalPrice;

    private Timestamp expiredDate;

}
