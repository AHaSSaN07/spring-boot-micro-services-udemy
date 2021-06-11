/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.api.albums.data;


import javax.persistence.*;

@Entity
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true,length = 50)
    private String albumId;
    @Column(nullable = true,length = 50)
    private String userId;
    @Column(nullable = true,length = 50)
    private String name;
    @Column(nullable = true,length = 50)
    private String description; 

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the albumId
     */
    public String getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId the albumId to set
     */
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
