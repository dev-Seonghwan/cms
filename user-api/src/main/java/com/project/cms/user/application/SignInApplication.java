package com.project.cms.user.application;

import static com.project.cms.user.exception.ErrorCode.LOGIN_CHECK_FAIL;

import com.project.cms.user.domain.SignInForm;
import com.project.cms.user.domain.model.Customer;
import com.project.cms.user.exception.CustomException;
import com.project.cms.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;

    public String customerLoginToken(SignInForm form) {
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return null;
    }

}
