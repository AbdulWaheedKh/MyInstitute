package com.smartSchool.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class AcademicSessionYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long instituteId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean current;
    private Date createdDate;
    private Date modifiedDate;
    private boolean deleted;

}
