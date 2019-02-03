/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.qrcodegenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private static final Logger LOG = LoggerFactory.getLogger(QRCodeServiceImpl.class);

    private static final String QR_CODE_IMAGE_FORMAT = "PNG";
    //public static final String QR_CODE_PATH = "./src/main/resources/static/QRCode.png";
    public static final String QR_CODE_PATH = Paths.get("static").toString();
    private static final int QR_CODE_WIDTH = 350;
    private static final int QR_CODE_HEIGHT = 350;

    private QRCodeWriter qrCodeWriter;

    @PostConstruct
    public void setUp() {
        qrCodeWriter = new QRCodeWriter();
    }

    @Override
    public void generate(String message) {

        LOG.debug("Generating QR Code with message: {}", message);

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(message, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT);

            Path path = FileSystems.getDefault().getPath(QR_CODE_PATH + "/QRCode.png");

            MatrixToImageWriter.writeToPath(bitMatrix, QR_CODE_IMAGE_FORMAT, path);

            LOG.info("Successfully generated QR Code");

        } catch (WriterException e) {
            LOG.error("Could not generate QR Code, WriterException :: {}", e.getMessage());
        } catch (IOException e) {
            LOG.error("Could not generate QR Code, IOException :: {}", e.getMessage());
        }
    }
}
