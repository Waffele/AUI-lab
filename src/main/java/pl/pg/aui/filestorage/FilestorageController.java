package pl.pg.aui.filestorage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/files")
public class FilestorageController {

    private final FilestorageService service;

    public FilestorageController(FilestorageService service) {
        this.service = service;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public void upload(@RequestPart MultipartFile multipart, String author) {
        service.upload(multipart, author);
    }

    @GetMapping("{id}")
    public byte[] download(@PathVariable Long id) {
        return service.download(id);
    }
}
