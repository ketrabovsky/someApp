package Factory;

import java.util.Map;

public interface AbstractFactory<T> {
     String[] getTypes();
     T make(Map<String, String> args);
     T make(String type, String args);
}
