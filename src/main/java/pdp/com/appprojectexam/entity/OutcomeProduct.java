package pdp.com.appprojectexam.entity;

import lombok.*;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "outcome_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OutcomeProduct extends AbsLongEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private OutcomeWarehouse outcomeWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Measurement measurement;

    private Double measurementAmount;

    private Double price;
}
