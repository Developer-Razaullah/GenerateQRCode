package com.example.qrcode.controller;

import com.example.qrcode.model.QrRequest;
import com.example.qrcode.service.QrGenerator;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QrController {

    private static final Logger log = LoggerFactory.getLogger(QrController.class);

    @PostMapping("qrCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody QrRequest qrRequest) {
        log.info("Received client Request");
        if (StringUtils.isBlank(qrRequest.getMessage())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "image/png");
            String message = qrRequest.getMessage();
            log.info("Generating QR image for {}", message);
            return new ResponseEntity<>(
                    QrGenerator.generateQR(message, 512, 512),
                    httpHeaders,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
