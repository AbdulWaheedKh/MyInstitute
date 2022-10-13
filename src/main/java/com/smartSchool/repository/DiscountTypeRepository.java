package com.smartSchool.repository;

import com.smartSchool.models.DiscountType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTypeRepository extends GenericRepository<DiscountType> {

    @Query("select d from DiscountType as d where d.id = :id and d.deleted = false")
    public DiscountType getByIdEntity(@Param("id") Long id);

}
