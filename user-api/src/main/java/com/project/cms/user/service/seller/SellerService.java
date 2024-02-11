package com.project.cms.user.service.seller;

import static com.project.cms.user.exception.ErrorCode.ALREADY_VERIFY;
import static com.project.cms.user.exception.ErrorCode.EXPIRE_CODE;
import static com.project.cms.user.exception.ErrorCode.NOT_FOUND_USER;
import static com.project.cms.user.exception.ErrorCode.WRONG_VERIFICATION;

import com.project.cms.user.domain.SignUpForm;
import com.project.cms.user.domain.model.Seller;
import com.project.cms.user.domain.repository.SellerRepository;
import com.project.cms.user.exception.CustomException;
import com.project.cms.user.exception.ErrorCode;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Optional<Seller> findByIdAndEmail(Long id, String email) {
        return sellerRepository.findByIdAndEmail(id, email);
    }

    public Optional<Seller> findValidSeller(String email, String password) {
        return sellerRepository.findByEmailAndPasswordAndVerifyIsTrue(email, password);
    }

    public Seller signUp(SignUpForm form) {
        return sellerRepository.save(Seller.form(form));
    }

    public boolean isEmailExist(String email) {
        return sellerRepository.findByEmail(email).isPresent();
    }
    @Transactional
    public void verifyEmail(String email, String code) {
        Seller seller = sellerRepository.findByEmail(email)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        if (seller.isVerify()) {
            throw new CustomException(ALREADY_VERIFY);
        } else if (!seller.getVerificationCode().equals(code)) {
            throw new CustomException(WRONG_VERIFICATION);
        } else if (seller.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(EXPIRE_CODE);
        }
        seller.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeSellerValidateEmail(Long customerId, String verificationCode) {
        Optional<Seller> sellerOptional = sellerRepository.findById(customerId);

        if (sellerOptional.isPresent()) {
            Seller seller = sellerOptional.get();
            seller.setVerificationCode(verificationCode);
            seller.setVerifyExpiredAt(LocalDateTime.now().plusHours(3));
            return seller.getVerifyExpiredAt();
        }
        throw new CustomException(NOT_FOUND_USER);
    }
}
