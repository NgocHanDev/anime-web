package vn.group04.animeweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String from, String to, String subject, String text){
        //MimeMailMessage => có đính kèm media
        //SimpleMailMessage => nội dung thông thường
        MimeMessage message = javaMailSender.createMimeMessage();
        //thực hiện gửi email
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);

        }catch (Exception e){
            throw new MailSendException("Gửi mail thất bại");
        }
        javaMailSender.send(message);
    }
}
