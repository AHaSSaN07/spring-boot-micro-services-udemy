/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.photoapp.api.albums.service;

import com.appsdeveloperblog.photoapp.api.albums.data.AlbumEntity;
import com.appsdeveloperblog.photoapp.api.albums.repos.AlbumsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumsServiceImpl implements AlbumsService {

    AlbumsRepo albumsRepo;

    @Autowired
    public AlbumsServiceImpl(AlbumsRepo albumsRepo){
        this.albumsRepo = albumsRepo;
    }

    @Override
    public List<AlbumEntity> getAlbums(String userId) {
        List<AlbumEntity> allUserAlbums = (List<AlbumEntity>)this.albumsRepo.findByUserId(userId);
        return allUserAlbums;
    }

    @Override
    public AlbumEntity createAlbum(AlbumEntity albumEntity) {
        AlbumEntity createdAlbum = null;
        if(albumEntity != null ){
            createdAlbum = this.albumsRepo.save(albumEntity);
        }
        return createdAlbum;
    }
}
