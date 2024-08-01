package com.company.reverselog.domain.requestProduct.repository;

import com.company.reverselog.domain.requestProduct.entity.RequestProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequestProductsRepository extends JpaRepository<RequestProduct, Long> {
}
