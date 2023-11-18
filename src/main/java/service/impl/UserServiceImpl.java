package service.impl;

import dao.Dao;
import dao.impl.UserDao;
import dto.UserDto;
import entity.User;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import proxy.DaoProxy;
import service.UserService;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Dao<User> userDao = new UserDao();
    private final UserMapper mapper = new UserMapperImpl();

    //return some empty filled with non exist values object
    @Override
    public UserDto get(UUID uuid) {
        Optional<User> received = userDao.get(uuid);
        return mapper.toUserDto(received.get());
    }

    @Override
    public List<UserDto> getAll() {
        List<User> received = userDao.getAll();
        return received.stream()
                .map(u -> mapper.toUserDto((User) u))
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {
        User user = mapper.toUser(userDto);
        userDao.save(user);
    }

    @Override
    public void update(UUID uuid, UserDto userDto) {
        User user = mapper.toUser(userDto);
        user.setId(uuid);
        userDao.save(user);
    }

    @Override
    public void delete(UUID uuid) {
        userDao.delete(uuid);
    }
}
