package pdp.com.appprojectexam.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import pdp.com.appprojectexam.entity.abs.AbsEntity;
import pdp.com.appprojectexam.entity.abs.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment_content")
@FieldNameConstants
public class AttachmentContent extends AbsLongEntity {

    private byte[] bytes;  //byte

    @OneToOne
    @ToString.Exclude
    private Attachment attachment;

}
