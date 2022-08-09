package pdp.com.appprojectexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.com.appprojectexam.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
