package com.smartSchool.repository;

import com.smartSchool.models.ClassCategory;
import com.smartSchool.models.StandardClass;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassCategoryRepository extends CrudRepository<ClassCategory, Long> {


    @Query("select d from ClassCategory as d where  d.deleted = false")
    public List<ClassCategory> getAllClassCategories(@Param("instituteId") Long instituteId, @Param("isDeleted") boolean isDeleted);

    @Modifying
    @Query(value = "UPDATE ClassCategory d SET d.deleted= 1 where d.id =:id ")
    public int delById(@Param("id") Long id);
}
