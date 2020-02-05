package Animals;

import java.util.Map;

public class Cat extends Animal {

    public Cat(String name) {
        this.setName(name);
    }

    public Cat(Map<String, String> args) {
        this.setName(args.get("name"));
    }

    public void getVoice() {
        System.out.println("I am a cat!");
    }

}
