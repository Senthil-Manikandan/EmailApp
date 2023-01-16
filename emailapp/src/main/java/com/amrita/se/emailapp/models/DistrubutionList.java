package com.amrita.se.emailapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "distrubutionlist",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "listname")
    })
public class DistrubutionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long Id;

    @Column(name = "listname")
    @NotBlank
    private String listName;

    @NotBlank
    @Column(name = "maillist")
    private String mailList;

}
