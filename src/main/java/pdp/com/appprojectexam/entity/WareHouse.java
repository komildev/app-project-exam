package pdp.com.appprojectexam.entity;


import lombok.*;
import lombok.experimental.FieldNameConstants;
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
@Entity(name = "ware_house")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update ware_house set deleted=true,status=false where id=?")
@FieldNameConstants
public class WareHouse extends AbsLongEntity {

    private String name;

    private boolean status = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WareHouse wareHouse = (WareHouse) o;
        return getId() != null && Objects.equals(getId(), wareHouse.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
