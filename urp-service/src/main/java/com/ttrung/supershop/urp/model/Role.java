package com.ttrung.supershop.urp.model;

import lombok.AllArgsConstructor;


@lombok.Getter
@lombok.Setter
@AllArgsConstructor
public class Role {

    public final static Role USER = new Role("USER");
    public final static Role ADMIN = new Role("ADMIN");

    private String name;
}

