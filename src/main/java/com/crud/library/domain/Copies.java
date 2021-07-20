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

    private int id;
    private Book book;
    private String status = "AVALIABLE";

    public Copies() {}

    public Copies(int id, Book book, String status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getId() {
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

    private void setId(int id) {
        this.id = id;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
