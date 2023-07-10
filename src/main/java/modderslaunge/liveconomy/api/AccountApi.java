package modderslaunge.liveconomy.api;

import modderslaunge.api.IAccountApi;
import modderslaunge.liveconomy.object.Account;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class AccountApi implements IAccountApi {
    private final MinecraftServer server;

    public AccountApi(MinecraftServer server) {
        this.server = server;
    }


    @Override
    public void addAccount(Account account) {

    }

    @Override
    public Account getAccount(String name) {
        return null;
    }

    @Override
    public void removeAccount(String name) {

    }

    @Override
    public void transfer(String name, long amount, String receiver) {

    }

    @Override
    public String changePassword(String New,String Old) {
        Old = New;
        return Old;
    }

    @Override
    public void login(String name, String password, Player p) {

    }

    @Override
    public boolean passwordCorrect(String name, String password) {
        return false;
    }

}
