package Animals;

public abstract class Animal implements AnimalI {
    private String name;

    protected void setName(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println("My name is: " + name);
    }
}
