package com.smartSchool.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@ToString
@Entity
public class Institute implements Serializable {

    private static final long serialVersionUID = 1567423423423423432L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long parentInstituteId;
    private String instituteName;
    private String instituteCode;
    private String supportNumber;
    private String supportEmail;
    private String address;
    private Date CreatedDate;
    private Date ModifiedDate;
    private boolean deleted;

//    @ManyToOne()
//    @JoinColumn(name = "ProfileId")
//    private Profile profile;

   public Institute() {
    }

    public Institute(Long id) {
        this.id = id;
    }

}
