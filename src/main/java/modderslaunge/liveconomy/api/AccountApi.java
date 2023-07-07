package modderslaunge.liveconomy.api;

import modderslaunge.api.IAccountApi;
import modderslaunge.liveconomy.object.Account;
import net.minecraft.server.MinecraftServer;

public class AccountApi implements IAccountApi {
    private MinecraftServer server;

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
}
