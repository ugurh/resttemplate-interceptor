package io.ugurh.sphub;

import io.ugurh.sphub.files.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author harun ugur
 * @project rest-template-interceptor
 * @created 13.04.2023 - 22:16
 */
@SpringBootApplication
public class SphubApplication implements CommandLineRunner {

    final FilesStorageService storageService;

    public SphubApplication(FilesStorageService storageService) {
        this.storageService = storageService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SphubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
    }
}
