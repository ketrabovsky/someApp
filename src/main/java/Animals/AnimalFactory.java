package Animals;

import Factory.AbstractFactory;

import java.util.Map;


public class AnimalFactory implements AbstractFactory<AnimalI> {
    private final String[] types = new String[]{"dog", "cat"};


    public AnimalI make(Map<String, String> args) {
        String type = args.get("type");
        String name = args.get("name");
        AnimalI animal = null;
        switch(type) {
            case "cat":
                animal = new Cat(name);
                break;
            case "dog":
                animal = new Dog(name);
                break;
            default:
                break;
        }

        return animal;
    }

    public String[] getTypes() {
        return types;
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
