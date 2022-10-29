package com.smartSchool.repository;

import com.smartSchool.models.DiscountType;
import com.smartSchool.models.StandardClass;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardClassRepository extends CrudRepository<StandardClass,Long> {

    @Query("select d from StandardClass as d where  d.deleted = false")
    public List<StandardClass> getAllStandardClasses(@Param("instituteId") Long instituteId, @Param("isDeleted") boolean isDeleted);


    @Modifying
    @Query(value = "UPDATE StandardClass d SET d.deleted= 1 where d.id =:id ")
    public int delById(@Param("id") Long id);
}
