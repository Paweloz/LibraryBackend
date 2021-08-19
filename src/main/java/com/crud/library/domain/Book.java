package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLICATION_YEAR")
    private int publicationYear;
    @OneToMany(
            targetEntity = Copies.class,
            mappedBy = "book",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Copies> copiesList = new ArrayList<>();

    public Book(String title, String author, int publicationYear, List<Copies> copiesList) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.copiesList = copiesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getPublicationYear() == book.getPublicationYear() && getId().equals(book.getId()) && getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), getPublicationYear());
    }
}
