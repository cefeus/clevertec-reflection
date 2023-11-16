package service;

import dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserDto get(UUID uuid);

    List<UserDto> getAll();

    void create(UserDto productDto);

    void update(UUID uuid, UserDto productDto);
    void delete(UUID uuid);
}
