package com.csi.mailcontroller;

import com.csi.mailmodel.Emailmodel;
import com.csi.mailservice.Emailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/app")
public class Emailcontroller {

    @Autowired
    Emailservice emailservice;

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendEmail(@RequestBody Emailmodel emailmodel) throws MessagingException {
        emailservice.sendEmail(emailmodel);
        return ResponseEntity.ok("Email Send Successfully");
    }
}
