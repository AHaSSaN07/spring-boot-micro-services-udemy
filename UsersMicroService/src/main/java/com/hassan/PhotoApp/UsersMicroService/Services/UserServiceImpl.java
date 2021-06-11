package com.hassan.PhotoApp.UsersMicroService.Services;

import com.hassan.PhotoApp.UsersMicroService.Models.AlbumResponseModel;
import com.hassan.PhotoApp.UsersMicroService.Models.AlbumsServiceClient;
import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;
import com.hassan.PhotoApp.UsersMicroService.Models.UserEntity;
import com.hassan.PhotoApp.UsersMicroService.Repos.UsersRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UsersRepo usersRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    Environment environment;
    AlbumsServiceClient albumsServiceClient;

    @Autowired
    UserServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder,RestTemplate restTemplate,
                    Environment environment,AlbumsServiceClient albumsServiceClient) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
        this.albumsServiceClient = albumsServiceClient;
    }

    @Override
    public UserDTO createUser(UserDTO userDetails) {
        userDetails.setUserID(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(this.bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);//same field names as the parent class
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        this.usersRepo.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserDetailsByUserName(String userName) {
        UserEntity user = usersRepo.findByEmail(userName);
        if (user == null)throw new UsernameNotFoundException("user not found");
        return new ModelMapper().map(user,UserDTO.class);
    }


    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity user = usersRepo.findByUserID(userId);
        if (user == null)throw new UsernameNotFoundException("user not found");
        String albumsUrl = String.format("http://localhost:12345/users/%s/albums",userId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");


        List<AlbumResponseModel> response = albumsServiceClient.getAlbums(userId,httpHeaders);
        UserDTO returnUSer = new ModelMapper().map(user,UserDTO.class);
        returnUSer.setAlbums(response);
        return returnUSer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = usersRepo.findByEmail(username);
        if (user == null)throw new UsernameNotFoundException("user not found");
        return new User(user.getEmail(),user.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
    }


}
