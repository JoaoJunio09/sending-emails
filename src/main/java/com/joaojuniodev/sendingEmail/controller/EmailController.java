package com.joaojuniodev.sendingEmail.controller;

import com.joaojuniodev.sendingEmail.model.EmailRequestDTO;
import com.joaojuniodev.sendingEmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email/v1")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        service.sendSimplesEmail(emailRequest);
        return new ResponseEntity<>("e-Mail sent with succcess!", HttpStatus.OK);
    }

    @PostMapping(value = "/withAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendEmailWithAttachment(
        @RequestParam("emailRequest") String emailRequestJson,
        @RequestParam("attachment") MultipartFile multipartFile
    ) {
        service.sendEmailWithAttachment(emailRequestJson, multipartFile);
        return new ResponseEntity<>("e-Mail attachment sent successfully!", HttpStatus.OK);
    }

}
