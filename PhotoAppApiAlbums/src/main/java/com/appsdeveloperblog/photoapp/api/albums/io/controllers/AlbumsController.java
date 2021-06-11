/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.api.albums.io.controllers;

import com.appsdeveloperblog.photoapp.api.albums.data.AlbumEntity;
import com.appsdeveloperblog.photoapp.api.albums.service.AlbumsService;
import com.appsdeveloperblog.photoapp.api.albums.ui.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {

    @Autowired
    AlbumsService albumsService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if (albumsEntities == null || albumsEntities.isEmpty()) {
            return returnValue;
        }

        Type listType = new TypeToken<List<AlbumResponseModel>>() {
        }.getType();

        returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
    },
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public AlbumEntity createAlbum(@RequestBody AlbumEntity albumEntity) {
        return this.albumsService.createAlbum(albumEntity);
    }

}
