package pdp.com.appprojectexam.entity;

import lombok.*;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "have_products")
public class HaveProducts extends AbsLongEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    private Double amount;

}
