/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.qrcodegenerator.controller;

import com.mycompany.qrcodegenerator.service.QRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QRCodeController {

    private static final Logger LOG = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping(value = "/generate")
    public String generateQRCode(@RequestParam("message") String message){
        LOG.info("Generating QR Code with message: {}", message);
        qrCodeService.generate(message);
        return "qrcode";
    }
}
