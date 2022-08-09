package pdp.com.appprojectexam.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pdp.com.appprojectexam.entity.abs.AbsEntity;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "product")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update product set deleted=true where id=?")
@FieldNameConstants
public class Product extends AbsLongEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Category category;


    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

    private String verificationCode ;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Measurement measurement;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
