package com.smartSchool.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long instituteId;
    private Long academicYearId;
    private String name;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    private boolean deleted;


}
