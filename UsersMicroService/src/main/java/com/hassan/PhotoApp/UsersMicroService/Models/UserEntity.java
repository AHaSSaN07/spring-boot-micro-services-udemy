package com.hassan.PhotoApp.UsersMicroService.Models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7276933160941394809L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false,length = 50)
    private String firstName;

    @Column(nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String userID;

    @Column(nullable = false,unique = false)
    private String encryptedPassword;

    //private List<AlbumResponseModel> albums;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
