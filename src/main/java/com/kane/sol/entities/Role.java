package com.kane.sol.entities;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
   public enum Roles{
        ADMIN, EMPLOYEE, MANAGER;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles name;


    public Long getId() {
        return id;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}