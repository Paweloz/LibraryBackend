package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQuery(
        name = "Copies.getAvaliable",
        query = "FROM Copies WHERE status = :AVALIABLE"
)

@Entity
@Table(name = "COPIES")
public class Copies {

    private Long id;
    private Book book;
    private String status = "AVALIABLE";

    public Copies() {}

    public Copies(Long id, Book book, String status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "COPY_ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    public Book getBook() {
        return book;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
