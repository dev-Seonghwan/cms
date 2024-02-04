package com.project.cms.user.application;

import com.project.cms.user.client.MailgunClient;
import com.project.cms.user.client.mailgun.SendMailForm;
import com.project.cms.user.domain.SignUpForm;
import com.project.cms.user.domain.model.Customer;
import com.project.cms.user.exception.CustomException;
import com.project.cms.user.exception.ErrorCode;
import com.project.cms.user.service.SignUpCustomerService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Customer c = signUpCustomerService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                .from("test@tester.com")
                .to(form.getEmail())
                .subject("Verification Email")
                .text(getVerificationEmailBody(c.getEmail(), c.getName(), code))
                .build();
//            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String code) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("hello ").append(name)
            .append("! Please Click Link for verification. \n\n")
            .append("http://localhost:8081/customer/verify/customer?email=")
            .append(email)
            .append("&code=")
            .append(code).toString();
    }


}
