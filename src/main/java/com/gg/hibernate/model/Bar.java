package com.gg.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * User: { "id": "hyardimci", "email":nosqlsolutions@gmail.com"}
 * Date: 5/26/13
 * Time: 2:19 PM
 */

@Embeddable
public class Bar {

    @Column(nullable = true)
    private boolean b;

    private Baz baz;

    @Transient
    private transient Bat bat;
}
