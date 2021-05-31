package com.hassan.PhotoApp.UsersMicroService.Services;

import com.hassan.PhotoApp.UsersMicroService.Models.UserDTO;
import com.hassan.PhotoApp.UsersMicroService.Models.UserEntity;
import com.hassan.PhotoApp.UsersMicroService.Repos.UsersRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UsersRepo usersRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepo = usersRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = usersRepo.findByEmail(username);
        if (user == null)throw new UsernameNotFoundException("user not found");
        return new User(user.getEmail(),user.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
    }


}
