package com.mycompany.qrcodegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QrCodeGeneratorApplication {

    static String IMAGE_DIR;

    public static void main(String[] args) {
        IMAGE_DIR = "img/";
        SpringApplication.run(QrCodeGeneratorApplication.class, args);
    }
}
