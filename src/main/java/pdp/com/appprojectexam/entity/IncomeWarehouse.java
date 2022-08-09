package pdp.com.appprojectexam.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "income_warehouse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class IncomeWarehouse extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private WareHouse wareHouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Currency currency;

    private Integer factureNumber;

    @Column(unique = true)
    private Long uniqueId;
}
