/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.qrcodegenerator.controller;

import com.mycompany.qrcodegenerator.service.QRCodeService;
import com.mycompany.qrcodegenerator.service.QRCodeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class QRCodeController {

    private static final Logger LOG = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping(value = "/generate")
    public String generateQRCode(@RequestParam("message") String message, Model model) throws IOException {
        LOG.info("Generating QR Code with message: {}", message);
        qrCodeService.generate(message);

        LOG.info("Path of file {}", applicationContext.getResource("QRCode.png").getFile().getName());

        model.addAttribute("qrimage", applicationContext.getResource("QRCode.png").getFile().getName());
        return "qrcode";
    }
}
