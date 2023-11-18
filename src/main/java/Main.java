import controller.SimpleController;

import java.util.UUID;

public class Main {

    private static final String JSON_USER = "{\"name\":\"Peter\",\"surname\":\"Parker\",\"email\":\"peterparker@gmail.com\",\"age\":25}";
    private static final String JSON_UPDATE_USER = "{\"name\":\"Petr\",\"surname\":\"Park\",\"email\":\"peterp@gmail.com\",\"age\":25}";
    private static final String XML_UPDATE_USER = "<UserDto><name>Petr</name><surname>Parer</surname><email>peter@gmail.com</email><age>24</age></UserDto>";
    private static final String EXISTING_UUID = "ce6db38a-b275-4a2a-9629-99e4748f85f9";
    private static final UUID ID = UUID.fromString(EXISTING_UUID);

    public static void main(String[] args) {
        SimpleController controller = new SimpleController();
        System.out.println(controller.get(ID));
        System.out.println(controller.getAll());
        System.out.println(controller.create(JSON_USER));
        System.out.println(controller.updateXML(ID, XML_UPDATE_USER));
        System.out.println(controller.update(ID, JSON_UPDATE_USER));
        System.out.println(controller.delete(ID));
    }

}
