package com.hassan.PhotoApp.UsersMicroService.Repos;

import com.hassan.PhotoApp.UsersMicroService.Models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
