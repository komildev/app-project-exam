package pdp.com.appprojectexam.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import pdp.com.appprojectexam.entity.abs.AbsEntity;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment")
@FieldNameConstants
public class Attachment extends AbsLongEntity {

    private String originalName;

    private long size;  //byte

    private String contentType;

    private String name;

    public Attachment(String originalName, long size, String contentType) {
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
    }
}
