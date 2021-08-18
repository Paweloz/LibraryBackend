package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQuery(
        name = "Copies.getAvaliable",
        query = "FROM Copies WHERE status = :AVALIABLE"
)

@Entity
@Table(name = "COPIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Copies {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "COPY_ID", unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Book book;
    @Column(name = "STATUS")
    private String status = "AVALIABLE";

    public Copies(Book book, String status) {
        this.book = book;
        this.status = status;
    }
}
