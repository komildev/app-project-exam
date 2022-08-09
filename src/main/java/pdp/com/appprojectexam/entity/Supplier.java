package pdp.com.appprojectexam.entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pdp.com.appprojectexam.entity.abs.AbsEntity;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "supplier")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update supplier set deleted=true where id=?")
public class Supplier extends AbsLongEntity {

    private String name;

    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
