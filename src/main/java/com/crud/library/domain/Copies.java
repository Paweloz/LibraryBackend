package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Copies {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "TITLE_ID")
    private String title_id;
    @Column(name = "STATUS")
    private String status;
}
