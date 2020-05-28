package com.nineleaps.springboot.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nineleaps.springboot.training.dtos.UserMsDto;
import com.nineleaps.springboot.training.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
		UserMapper INSTANCE =Mappers.getMapper(UserMapper.class);

		UserMsDto userToUserMsDto(User user);
		
		List<UserMsDto> userToUserMsDtos(List<User> users);
}
