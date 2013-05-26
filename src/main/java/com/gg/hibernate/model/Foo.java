package com.gg.hibernate.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:43 AM
 */

@Entity
public class Foo {


    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
