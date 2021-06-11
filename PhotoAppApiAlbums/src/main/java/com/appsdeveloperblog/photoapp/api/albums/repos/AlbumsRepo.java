package com.appsdeveloperblog.photoapp.api.albums.repos;

import com.appsdeveloperblog.photoapp.api.albums.data.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlbumsRepo extends CrudRepository<AlbumEntity,Long> {
    Iterable<AlbumEntity> findByUserId(String userId);
}
