import Animals.AnimalI;
import Animals.AnimalFactory;
import AppConfiguration.AppConfiguration;
import Factory.AbstractFactory;
import Logger.Handlers.FileHandler;
import Logger.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class App {
    private final static String configurationFilename = "config/factories/factories.xml";
    private static AbstractFactory<AnimalI> animalFactory;
    private static Logger logger;

    private static void setup() {
        logger = new Logger();
        logger.registerHandler(new FileHandler("critical.log"));
    }

    private static void teardown() {
        logger.cleanup();
    }

    public static void main(String[] args) {
        setup();
        logger.debug("This is debug log");
        logger.info("This is info log");
        logger.error("This is error log");
        logger.warning("This is warning log");
        xml_main(logger);
        teardown();
    }

    private static void xml_main(Logger logger) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document doc = builder.parse(configurationFilename);
            NodeList factoryList = doc.getElementsByTagName("factory");
            for (int i = 0; i < factoryList.getLength(); i++) {
                Node p = factoryList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element factory = (Element) p;
                    String str = factory.getAttribute("class");
                    logger.debug(String.format("Some node: %s", str));
                    System.out.println(str);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<AnimalI> createAnimals(AppConfiguration config) {
        ArrayList<AnimalI> animals = new ArrayList<AnimalI>();
        for (HashMap<String, String> map : config.getAnimals()) {
            AnimalI a = animalFactory.make(map);
            animals.add(a);
        }
        return animals;
    }
}
