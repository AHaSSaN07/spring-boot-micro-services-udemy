package com.hassan.PhotoApp.UsersMicroService.Models;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

//contains user details to be moved among different layers
public class UserDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = -4761691313653129420L;

    private String firstName;

    private String lastName;

    private String password;

    private String email;
    private String userID;

    private String encryptedPassword;

    private List<AlbumResponseModel> albums;

    public List<AlbumResponseModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResponseModel> albums) {
        this.albums = albums;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
