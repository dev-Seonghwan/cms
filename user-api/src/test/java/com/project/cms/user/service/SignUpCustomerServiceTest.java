package com.project.cms.user.service;

import static org.junit.jupiter.api.Assertions.*;

import com.project.cms.user.domain.SignUpForm;
import com.project.cms.user.domain.model.Customer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignUpCustomerServiceTest {


    @Autowired
    private SignUpCustomerService service;


    @Test
    void signUp() {
        SignUpForm form = SignUpForm.builder()
            .name("name")
            .birth(LocalDate.now())
            .email("abc@naver.com")
            .password("1111")
            .phone("01012345678")
            .build();

        Customer c = service.signUp(form);
        assertNotNull(c.getId());
    }
}