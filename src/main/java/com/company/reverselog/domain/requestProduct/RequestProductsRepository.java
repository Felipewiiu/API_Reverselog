package com.company.reverselog.domain.requestProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RequestProductsRepository extends JpaRepository<RequestProduct, Long> {
}
