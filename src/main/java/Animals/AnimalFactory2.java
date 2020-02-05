package Animals;

import Factory.AbstractFactory;

import java.util.Map;

public class AnimalFactory2 implements AbstractFactory<AnimalI> {
    private final String[] types = new String[]{"dog", "cat"};


    public String[] getTypes() {
        return types;
    }

    public AnimalI make(Map<String, String> args) {
        String type = args.get("type");
        if (type.equals("cat")) {
            return new Cat(args);
        } else if (type.equals("dog")) {
            return new Dog(args);
        }
        return null;
    }

    public AnimalI make(String type, String name) {
        if (type.equals("cat")) {
            return new Cat(name);
        } else if (type.equals("dog")) {
            return new Dog(name);
        }
        return null;
    }
}
