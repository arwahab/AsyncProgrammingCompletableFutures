package com.asyncprogramming.demo.objects;

public class Thief {

    public static Thief ULLOO = new Thief("Ulloo");
    public static Thief KA = new Thief("Ka");
    public static Thief PATTA = new Thief("Patta");

    private final String name;

    private Thief(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String handleLoot(final Loot loot) {
        switch (loot) {
        case NICE:
            return name + " [When getting the loot '" + loot.getMsg() + "'] : Wooooowww amazing!!";
        case NOT_BAD:
            return name + " [When getting the loot '" + loot.getMsg()
                    + "'] : Hmmm what a disappointment! Now I need to buy the Han Solo figure...";
        case BAD:
            return name + " [When getting the loot '" + loot.getMsg() + "'] : Grrrrr :(";
        }
        throw new IllegalArgumentException("Unexpected Loot: " + loot);
    }
}
