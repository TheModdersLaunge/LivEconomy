package modderslaunge.liveconomy.object;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private final String name;
    private String password;
    private long balance;
    private final UUID creator;
    private ArrayList<Card> cards;

    public Account(String name, String password, UUID creator) {
        this.name = name;
        this.password = password;
        this.creator = creator;
        this.balance = 0;
    }

    public ArrayList<Card> getCards() {return cards;}

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public long getBalance() {
        return balance;
    }

    public UUID getCreator() {
        return creator;
    }
}
