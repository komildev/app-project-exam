package pdp.com.appprojectexam.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "outcome_warehouse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class OutcomeWarehouse extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private WareHouse wareHouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Currency currency;


    @Column(unique = true)
    private String uniqueId;
}
