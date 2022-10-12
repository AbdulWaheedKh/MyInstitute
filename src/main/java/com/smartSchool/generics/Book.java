package com.smartSchool.generics;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Book implements Serializable, GenericEntity<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;



    @Override
    public void update(Book source) {
        this.title = source.getTitle();

    }

    @Override
    public Book createNewInstance() {
        Book newInstance = new Book();
        newInstance.update(this);

        return newInstance;
    }


}
