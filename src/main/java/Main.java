import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import controller.SimpleController;
import dto.UserDto;
import service.impl.UserServiceImpl;

import java.util.UUID;

public class Main {

    private static final String JSON_USER = "{\"name\":\"Peter\",\"surname\":\"Parker\",\"email\":\"peterparker@gmail.com\",\"age\":25}";
    private static final String JSON_UPDATE_USER = "{\"name\":\"Petr\",\"surname\":\"Park\",\"email\":\"peterp@gmail.com\",\"age\":25}";
    private static final String XML_UPDATE_USER = "<UserDto><name>Petr</name><surname>Parer</surname><email>peter@gmail.com</email><age>24</age></UserDto>";
    private static final String EXISTING_UUID = "ce6db38a-b275-4a2a-9629-99e4748f85f9";
    private static final UUID ID = UUID.fromString(EXISTING_UUID);

    public static void main(String[] args) throws JsonProcessingException {
        SimpleController controller = new SimpleController();
        controller.get(ID);
        controller.getAll();
        controller.create(JSON_USER);
        controller.updateXML(ID, XML_UPDATE_USER);
        controller.update(ID, JSON_UPDATE_USER);
        controller.delete(ID);
    }
}
