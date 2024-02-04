package com.project.cms.user.controller;

import com.project.cms.user.application.SignUpApplication;
import com.project.cms.user.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }


    @PutMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomer(@RequestParam(value="email")String email,@RequestParam(value="code") String code){
        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("인증이 완료 되었습니다.");
    }

}
