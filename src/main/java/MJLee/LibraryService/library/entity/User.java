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

    @Column
    Date createUserTime;

    @Column(unique = true)
    String nickName;

    @Column
    Date startRent;

    @Column
    Date deadlineRent;

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
}
