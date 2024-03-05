package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExExchanger {
    public static void main(String[] args) {
        Exchanger<Action> exchanger = new Exchanger<>();
        List<Action> myList = new ArrayList<>();
        myList.add(Action.BUMAGA);
        myList.add(Action.KAMEN);
        myList.add(Action.NOJNIZI);

        List<Action> friendsList = new ArrayList<>();
        friendsList.add(Action.KAMEN);
        friendsList.add(Action.BUMAGA);
        friendsList.add(Action.BUMAGA);

        new BestFriends("George", exchanger, myList);
        new BestFriends("Nikita", exchanger, friendsList);
    }
}

enum Action {
    KAMEN, NOJNIZI, BUMAGA;
}

class BestFriends extends Thread {
    String name;
    Exchanger<Action> exchanger;
    List<Action> actionList;

    public BestFriends(String name, Exchanger<Action> exchanger, List<Action> actionList) {
        this.name = name;
        this.exchanger = exchanger;
        this.actionList = actionList;
        this.start();
    }

    public void whoIsWinner(Action me, Action friend) {
        if ((me == Action.BUMAGA && friend == Action.KAMEN)
        || (me == Action.KAMEN && friend == Action.NOJNIZI)
        || (me == Action.NOJNIZI && friend == Action.BUMAGA)) {
            System.out.println(name + " is winner!!!");
        }
    }

    @Override
    public void run() {
        Action other;
        for (Action a: actionList
             ) {
            try {
                other = exchanger.exchange(a);
                whoIsWinner(a, other);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}