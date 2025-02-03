package MJLee.LibraryService.library.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String userName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date createUserTime;

    @Column(unique = true)
    String nickName;

    @Column
    Long rentedBooks = 0L;

    @Column
    Long delayDays = 0L;

    @Column
    boolean canRent = true;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateUserTime() {
        return createUserTime;
    }

    public void setCreateUserTime(Date createUserTime) {
        this.createUserTime = createUserTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isCanRent() {
        return canRent;
    }

    public void setCanRent(boolean canRent) {
        this.canRent = canRent;
    }

    public Long getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(Long rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public Long getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Long delayDays) {
        this.delayDays = delayDays;
    }
}
