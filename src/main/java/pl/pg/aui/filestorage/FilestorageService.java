package pl.pg.aui.filestorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilestorageService {
    private final FileInfoRepository repository;

    private String uploadDir = "uploads/";

    public FilestorageService(FileInfoRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() throws IOException {
        if(!Files.exists(Path.of(uploadDir)))
            Files.createDirectory(Path.of("uploads"));
    }

    @Transactional
    public void upload(MultipartFile multipart, String author) {
        saveUploadedFile(multipart);
        repository.save(new FileInfo(author, multipart.getOriginalFilename()));
    }

    private String saveUploadedFile(MultipartFile multipart) {
        try {
            Path path = Path.of(uploadDir + multipart.getOriginalFilename());
            multipart.transferTo(path);
            return path.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public byte[] download(Long id) {
        FileInfo info = repository.getById(id);
        try {
            Path path = Path.of(uploadDir + info.getFilename());
            return Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
