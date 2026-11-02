// Demonstration of Inheritance and Method Overriding in Java
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void sound() {
        System.out.println(name + " makes a generic animal sound.");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " meows: Meow~");
    }
}

public class animal {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");

        dog.sound();
        cat.sound();
    }
}
