package com.project.cms.user.domain.repository;

import com.project.cms.user.domain.model.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//@Hidden
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional <Customer> findByEmail(String email);


}
