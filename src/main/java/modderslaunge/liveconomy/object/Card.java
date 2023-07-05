package modderslaunge.liveconomy.object;


import modderslaunge.liveconomy.ServerConfig;
import modderslaunge.liveconomy.core.ModItems;
import net.minecraft.world.item.ItemStack;

public class Card {

    private final int creationDate;
    private final int expiryDate;
    private final Account account;

    private final ItemStack card;
    private final Color color;

    private String PIN;

    public Card(int creationDate, Account account, String PIN, Color color) {
        this.creationDate = creationDate;
        this.expiryDate = creationDate + ServerConfig.CARD_EXPIRES_AFTER.get();
        this.account = account;
        this.PIN = PIN;
        this.color = color;
        this.card = new ItemStack(ModItems.CARD.get());
    }

    public int getCreationDate() {
        return creationDate;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public Account getAccount() {
        return account;
    }

    public String getPIN() {
        return PIN;
    }

    public enum Color {
        LIME,
        YELLOW,
        ORANGE,
        RED,
        RAINBOW,
        BLACK,
        PURPLE,
        CYAN
    }
}
