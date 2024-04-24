package com.project.cms.user.domain.repository;

import com.project.cms.user.domain.model.CustomerBalanceHistory;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Optional;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
//@Hidden
public interface CustomerBalanceHistoryRepository extends
    JpaRepository<CustomerBalanceHistory, Long> {

    Optional<CustomerBalanceHistory> findFirstByCustomer_IdOrderByIdDesc(
        @RequestParam("customer_id") Long customerId);
}
