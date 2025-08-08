package com.kane.sol.entities;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
   public enum Roles{
        ADMIN, EMPLOYEE;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles name;

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}