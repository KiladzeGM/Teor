package Test;

import java.util.function.Consumer;

public class Power {

    public static void main(String[] args) {
        Runnable say = Duo::say;
        say.run();
    }

}


class Duo {
    public static void say() {
        System.out.println("Hi!");
    }
}

@FunctionalInterface
interface Say {

    void say();

}
