package com.project.cms.user.application;

import static com.project.cms.user.exception.ErrorCode.LOGIN_CHECK_FAIL;

import com.project.cms.user.domain.SignInForm;
import com.project.cms.user.domain.model.Customer;
import com.project.cms.user.domain.model.Seller;
import com.project.cms.user.exception.CustomException;
import com.project.cms.user.service.customer.CustomerService;
import com.project.cms.user.service.seller.SellerService;
import com.project.domain.config.JwtAuthenticationProvider;
import com.project.domain.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form) {
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form) {
        Seller s = sellerService.findValidSeller(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        return provider.createToken(s.getEmail(), s.getId(), UserType.SELLER);
    }
}
