package mapper;

import dto.UserDto;
import entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    /**
     * Маппит DTO в пользователя без UUID
     *
     * @param userDto - DTO для маппинга
     * @return новый пользователь
     */
    @Mapping(target = "id", ignore = true)
    User toUser(UserDto userDto);

    /**
     * Маппит пользователя в DTO без UUID
     *
     * @param user - пользователь для маппинга
     * @return новое DTO
     */
    UserDto toUserDto(User user);
}
