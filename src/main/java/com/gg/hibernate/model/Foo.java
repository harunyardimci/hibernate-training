package com.gg.hibernate.model;


import javax.persistence.*;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 11:43 AM
 */

@Entity(name="foobar")
@Table(name="T_FOO")
public class Foo {

    @Column(name="C_NAME")
    private String name;

    private Bar bar;

    public Foo() {

    }

    public Foo(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    public String getName() {
        return name;
    }


    public Long getId() {
        return id;
    }
}
