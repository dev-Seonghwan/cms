package com.project.cms.user.domain.repository;

import com.project.cms.user.domain.model.Seller;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
@Hidden
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByIdAndEmail(Long id, String email);
    Optional<Seller> findByEmailAndPasswordAndVerifyIsTrue(String email, String password);

    Optional<Seller> findByEmail(String email);


}
