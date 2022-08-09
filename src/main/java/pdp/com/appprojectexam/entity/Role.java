package pdp.com.appprojectexam.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;
import pdp.com.appprojectexam.enums.PermissionEnum;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
@Getter
@Setter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update roles set deleted=true where id=?")
public class Role extends AbsLongEntity{

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<PermissionEnum> permissions;

}
