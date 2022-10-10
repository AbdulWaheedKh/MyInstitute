package com.myinstitute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;


import java.util.List;
@NoRepositoryBean
public interface GenericRepository <T extends Object> extends JpaRepository<T, Long> {

//    <S extends T> S save(S entity);

    @Query("select t from #{#entityName} as t  where  t.id = :id and t.deleted =:isDeleted order by t.id desc")
    public List<T> getAll(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);
//
//    @Query("select t from #{#entityName} as t  where  t.institute.id = :id and t.deleted =:isDeleted order by t.id desc")
//    public List<T> getAllActiveByInstituteId(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);
//
//    @Query("select t from #{#entityName} as t  where  t.institute.id = :id and t.deleted =:isDeleted order by t.modifiedDate Desc")
//    public List<T> getAllActiveByInstituteIdOrderByModifiedDate(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);

    @Query("select t from #{#entityName} as t where t.id = :id")
    public T getById(@Param("id") Long id);

    @Modifying
    @Query("Update #{#entityName} t set t.deleted = :isDeleted , modifiedDate = CURRENT_TIMESTAMP where t.id =:id")
    public int markAsDeletedById(@Param("id") Long id, @Param("isDeleted") boolean isDeleted);
}
