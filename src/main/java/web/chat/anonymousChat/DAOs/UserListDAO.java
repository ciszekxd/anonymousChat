package web.chat.anonymousChat.DAOs;

import web.chat.anonymousChat.UserStatusEnum;
import web.chat.anonymousChat.repositories.UserListRepository;

import javax.persistence.*;

@Entity(name = "user_list_entity")
@Table(name = "user_list")
public class UserListDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hash;
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    public UserListDAO(int id, String hash, UserStatusEnum status) {
        this.id = id;
        this.hash = hash;
        this.status = status;
    }

    public UserListDAO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }
}
