package modderslaunge.api;

import modderslaunge.liveconomy.object.Account;
import net.minecraft.world.entity.player.Player;

public interface IAccountApi {
    void addAccount(Account account);

    Account getAccount(String name);

    void removeAccount(String name); // by  creator or admin


    void login(String name, String password, Player p);

    boolean passwordCorrect(String name, String password);
  
    void transfer(String name, long amount, String receiver);
}
