package com.pms.user.service;

import com.pms.user.model.UserEmail;
import com.pms.user.model.Users;
import com.pms.user.repository.EmailRepository;
import com.pms.user.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${localSever}")
    private String serverIp;

    @Value("${server.port}")
    private String portNumber;

    @Value("${forgetPasswordUrl}")
    private String forgetPasswordUrl;


    @Override
    public boolean sendEmail(UserEmail emailEntity, Users user) {
        boolean isEmailSent = false;
        boolean isHtml = true;
        try {
            if(emailEntity!=null && user!=null){
                if (emailEntity.getEmailType().equals(Constant.Email.Type.FORGETPASSWORDEMAIL)){
                    emailEntity = forgetPasswordContent(emailEntity,user);
                }
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setSubject(emailEntity.getEmailSubject());
                helper.setFrom(senderEmail);
                helper.setTo(user.getEmail());
                helper.setText(emailEntity.getEmailBody(),isHtml);
                mailSender.send(message);
                emailEntity.setIsEmailSent(Constant.Email.EmailSentFailed);
                isEmailSent = true;
                emailEntity.setIsEmailSent(Constant.Email.EmailSent);
                addEmail(emailEntity);
            }
        }catch (Exception e){
            emailEntity.setIsEmailSent(Constant.Email.EmailSentFailed);
            addEmail(emailEntity);
            e.printStackTrace();
        }
        return isEmailSent;
    }

    public UserEmail addEmail(UserEmail userEmail){
        try {
            userEmail.setCreatedAt(LocalDateTime.now());
            userEmail.setIsActive(1);
            return emailRepository.save(userEmail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  userEmail;
    }

    private UserEmail forgetPasswordContent(UserEmail userEmail, Users users){
        try {
            userEmail.setCreatedBy(users.getUserId().longValue());
            userEmail.setEmailSubject(Constant.Email.Subject.ForgetEmail);
            userEmail.setEmailBody("<b>Hi "+users.getFirstName()+"</b>,<br><i>Please verify the passcode : </i>" +
                    "<b>"+userEmail.getAuthCode()+"</b></p>" +
                    "<br>Please click on the link for updating new password and use passcode for verfication" +
                    "<a href="+serverIp+forgetPasswordUrl+">Set new password</a>");
        }catch (Exception e){
            e.printStackTrace();
        }
        return userEmail;
    }
}

