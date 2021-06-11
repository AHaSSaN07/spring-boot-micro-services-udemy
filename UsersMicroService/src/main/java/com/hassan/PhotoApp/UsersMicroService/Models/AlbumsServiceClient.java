package com.hassan.PhotoApp.UsersMicroService.Models;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "albums-ws",url = "localhost:12345")
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    public List<AlbumResponseModel> getAlbums(@PathVariable String id, @RequestHeader HttpHeaders httpHeaders);

}
