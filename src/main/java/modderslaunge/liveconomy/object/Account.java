package modderslaunge.liveconomy.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Account {
    private final String name;
    private String password;
    private long balance;
    private final UUID creator;
    private ArrayList<Card> cards;

    private ArrayList<UUID> loggedIn;

    public Account(String name, String password, UUID creator) {
        this.name = name;
        this.password = password;
        this.creator = creator;
        this.balance = 0;
        this.loggedIn = new ArrayList<>(Collections.singleton(creator));

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

    public void changePassword(String newP) {
        this.password = newP;
    }

    public long getBalance() {
        return balance;
    }

    public UUID getCreator() {
        return creator;
    }

    public long addFunds(long amount) {
        balance += amount;
        return balance;
    }

    public long removeFunds(long amount) {
        balance -= amount;
        return balance;
    }

    public ArrayList<UUID> getLoggedIn() {
        return loggedIn;
    }

    public void removeLoggedIn(UUID user) {
        this.loggedIn.remove(user);
    }

    public void addLoggedIn(UUID user) {
        this.loggedIn.add(user);
    }
}
