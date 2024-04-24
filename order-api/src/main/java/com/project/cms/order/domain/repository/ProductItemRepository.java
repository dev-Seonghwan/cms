package com.project.cms.order.domain.repository;

import com.project.cms.order.domain.model.Product;
import com.project.cms.order.domain.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
