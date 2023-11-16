package service.impl;

import dao.Dao;
import dao.impl.UserDao;
import dto.UserDto;
import entity.User;
import proxy.DaoProxy;
import service.UserService;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static Dao userDao = (Dao) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), new Class[]{ Dao.class }, new DaoProxy(new UserDao()));


    @Override
    public UserDto get(UUID uuid) {
        Optional<User> received = userDao.get(uuid);
        //mapping
        return new UserDto();
    }

    @Override
    public List<UserDto> getAll() {
        List received = userDao.getAll();
        return received.stream().map(
                // mapping
        ).collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {
        User user;
        //map dto to user
        userDao.save(user);
    }

    @Override
    public void update(UUID uuid, UserDto userDto) {
        User user;
        // map dto to user and set uuid
        userDao.save();
    }

    @Override
    public void delete(UUID uuid) {
        userDao.delete(uuid);
    }
}
