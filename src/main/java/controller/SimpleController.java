package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dto.UserDto;
import lombok.SneakyThrows;
import service.UserService;
import service.impl.UserServiceImpl;
import validation.Validator;

import java.util.UUID;

public class SimpleController {

    private UserService service;
    private ObjectMapper jsonMapper;
    private XmlMapper xmlMapper;

    public SimpleController() {
        service = new UserServiceImpl();
        jsonMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    @SneakyThrows(JsonProcessingException.class)
    public String get(UUID id) {
        var user = service.get(id);
        return jsonMapper.writeValueAsString(user);
    }

    @SneakyThrows(JsonProcessingException.class)
    public String getAll() {
        var users = service.getAll();
        return jsonMapper.writeValueAsString(users);
    }

    @SneakyThrows(JsonProcessingException.class)
    public String create(String user) {
        var save = jsonMapper.readValue(user, UserDto.class);
        Validator.validate(save);
        service.create(save);
        return "201";
    }

    @SneakyThrows(JsonProcessingException.class)
    public String update(UUID id, String user) {
        var save = jsonMapper.readValue(user, UserDto.class);
        Validator.validate(save);
        service.update(id, save);
        return "201";
    }

    @SneakyThrows(JsonProcessingException.class)
    public String updateXML(UUID id, String user) {
        var save = xmlMapper.readValue(user, UserDto.class);
        Validator.validate(save);
        service.update(id, save);
        return "201";
    }

    public String delete(UUID id) {
        service.delete(id);
        return "200";
    }
}
