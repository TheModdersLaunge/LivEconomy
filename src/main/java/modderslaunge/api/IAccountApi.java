package modderslaunge.api;

import modderslaunge.liveconomy.object.Account;
import net.minecraft.world.entity.player.Player;

public interface IAccountApi {
    void addAccount(Account account);

    Account getAccount(String name);

    void removeAccount(String name); // by  creator or admin


    boolean changePassword(String name, String newPassword, String old);

    boolean login(String name, String password, Player p);

    boolean passwordCorrect(String name, String password);
  
    long transfer(String name,long amount,long userBalance, String receiver);

}
