package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.AttachmentDTO;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER_PATH)
public interface AttachmentController {
    String ATTACHMENT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/attachment/";
    String UPLOAD_PATH = "upload";
    String DOWNLOAD_PATH = "download";

    @PreAuthorize(value = "hasAuthority('UPLOAD_ATTACHMENT')")
    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request);

    @PreAuthorize(value = "hasAuthority('DOWNLOAD_ATTACHMENT')")
    @GetMapping(DOWNLOAD_PATH + "/{id}")
    void download(@PathVariable Integer id,
                  @RequestParam(defaultValue = "false") boolean inline,
                  HttpServletResponse response);
}
