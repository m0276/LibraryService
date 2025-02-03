package MJLee.LibraryService.library.entity;


import jakarta.persistence.*;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String author;

    @Column
    boolean rent;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date startRent;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date deadlineRent;

    @ManyToOne
    @JoinColumn(name = "user")
    User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(Date startRent) {
        this.startRent = startRent;
    }

    public Date getDeadlineRent() {
        return deadlineRent;
    }

    public void setDeadlineRent(Date deadlineRent) {
        this.deadlineRent = deadlineRent;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", rent=" + rent +
                ", user=" + user +
                '}';
    }
}
