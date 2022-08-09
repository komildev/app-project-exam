package pdp.com.appprojectexam.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.AttachmentDTO;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request);

    ApiResult<AttachmentDTO> uploadSystem(MultipartHttpServletRequest request);

    void downloadDb(Integer id, boolean inline, HttpServletResponse response);

    void downloadSystem(Integer id, boolean inline, HttpServletResponse response);
}
