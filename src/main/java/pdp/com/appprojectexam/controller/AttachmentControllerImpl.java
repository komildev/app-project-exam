package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.com.appprojectexam.controller.AttachmentController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.AttachmentDTO;
import pdp.com.appprojectexam.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {
    private final AttachmentService attachmentService;


    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Integer id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id, inline, response);
    }
}
