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
@Entity(name = "measurement")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update measurement set deleted=true,status=false where id=?")

public class Measurement extends AbsLongEntity {

    private String name;

    private boolean status = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Measurement that = (Measurement) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
