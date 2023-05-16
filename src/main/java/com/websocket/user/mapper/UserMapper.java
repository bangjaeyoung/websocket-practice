package com.websocket.user.mapper;

import com.websocket.user.dto.UserDto;
import com.websocket.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostDtoToUser(UserDto.Post postDto);
}
