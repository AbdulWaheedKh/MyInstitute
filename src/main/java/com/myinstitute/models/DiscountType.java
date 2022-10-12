package com.myinstitute.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;

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
