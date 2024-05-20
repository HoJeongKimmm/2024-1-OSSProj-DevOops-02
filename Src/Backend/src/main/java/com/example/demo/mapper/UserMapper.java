package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}