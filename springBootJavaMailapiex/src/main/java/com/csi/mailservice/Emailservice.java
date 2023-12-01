package com.csi.mailservice;

import com.csi.mailmodel.Emailmodel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class Emailservice {

    @Autowired
    private JavaMailSender mailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

    }

    public void sendEmail(Emailmodel emailModel) throws MessagingException {

        log.info("*************TO EMAIL*********: " + emailModel.getEmailTo());

        log.info("*************SUBJECT*********: " + emailModel.getEmailSubject());

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("beamolpatil@gmail.com");
        mimeMessageHelper.setTo(emailModel.getEmailTo());
        mimeMessageHelper.setCc(emailModel.getEmailCC());


        mimeMessageHelper.setText(emailModel.getEmailBody());
        mimeMessageHelper.setSubject(emailModel.getEmailSubject());

        FileSystemResource fileSystem
                = new FileSystemResource(new File(emailModel.getEmailAttachment()));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailSender.send(mimeMessage);
        log.info("Mail Send Successfully...");

    }
}
