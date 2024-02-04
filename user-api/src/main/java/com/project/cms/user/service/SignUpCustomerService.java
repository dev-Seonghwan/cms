package com.project.cms.user.service;

import static com.project.cms.user.exception.ErrorCode.ALREADY_VERIFY;
import static com.project.cms.user.exception.ErrorCode.EXPIRE_CODE;
import static com.project.cms.user.exception.ErrorCode.NOT_FOUND_USER;
import static com.project.cms.user.exception.ErrorCode.WRONG_VERIFICATION;

import com.project.cms.user.domain.SignUpForm;
import com.project.cms.user.domain.model.Customer;
import com.project.cms.user.domain.repository.CustomerRepository;
import com.project.cms.user.exception.CustomException;
import com.project.cms.user.exception.ErrorCode;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.form(form));
    }

    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code){
        System.out.println(email);
        Customer customer = customerRepository.findByEmail(email)
            .orElseThrow(()-> new CustomException(NOT_FOUND_USER));
        if(customer.isVerify()){
            throw new CustomException(ALREADY_VERIFY);
        } else if(!customer.getVerificationCode().equals(code)){
            throw new CustomException(WRONG_VERIFICATION);
        } else if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(EXPIRE_CODE);
        }
        customer.setVerify(true);

    }

    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusHours(3));
            return customer.getVerifyExpiredAt();
        }
        throw new CustomException(NOT_FOUND_USER);
    }

}
