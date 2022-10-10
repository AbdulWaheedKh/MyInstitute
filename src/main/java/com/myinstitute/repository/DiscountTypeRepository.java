package com.myinstitute.repository;

import com.myinstitute.models.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTypeRepository extends GenericRepository<DiscountType> {
}
