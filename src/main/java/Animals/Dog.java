package Animals;

import java.util.Map;

public class Dog extends Animal {

    public Dog(String name) {
        setName(name);
    }

    public Dog(Map<String, String> args) {
        setName(args.get("name"));
    }

    public void getVoice() {
        System.out.println("I am a dog!");
    }

}
