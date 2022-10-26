package com.smartSchool.repository;

import com.smartSchool.models.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {

    @Query("select d from DiscountType as d where d.id = :id and d.deleted = false")
    public DiscountType getByIdEntity(@Param("id") Long id);

    @Query("select d from DiscountType as d where  d.deleted = false")
    public List<DiscountType> getAllDiscountTypes(@Param("instituteId") Long instituteId, @Param("isDeleted") boolean isDeleted);

    @Modifying
    @Query(value = "UPDATE DiscountType d SET d.deleted= 1 where d.id =:id ")
    public int delById(@Param("id") Long id);

}
