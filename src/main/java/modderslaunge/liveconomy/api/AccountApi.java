package modderslaunge.liveconomy.api;

import modderslaunge.api.IAccountApi;
import modderslaunge.liveconomy.object.Account;
import modderslaunge.liveconomy.saveddata.AccountSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class AccountApi implements IAccountApi {
    private final MinecraftServer server;

    public AccountApi(MinecraftServer server) {
        this.server = server;
    }


    @Override
    public void addAccount(Account account) {
        AccountSavedData data = AccountSavedData.get(server.overworld());
        data.addAccount(account);
        data.setDirty();
        ModLogger.info(String.format("ACCOUNT PUSHED: %s BY %s", account.getName(), account.getCreator()));
    }

    @Override
    public Account getAccount(String name) {
        return AccountSavedData.get(server.overworld()).getAccount(name);
    }

    @Override
    public void removeAccount(String name) {
        AccountSavedData data = AccountSavedData.get(server.overworld());
        data.removeAccount(name);
        data.setDirty();
        ModLogger.info(String.format("ACCOUNT REMOVED: %s", name));
    }

    @Override
    public void transfer(String name, long amount, String receiver) {
        AccountSavedData data = AccountSavedData.get(server.overworld());

        if (data.getAccount(name) == null || data.getAccount(receiver) == null) {
            throw new NullPointerException(String.format("ATTEMPTED TRANSFER FROM %s TO %s, BUT ACCOUNT/S EQUAL/S NULL", name, receiver));
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(String.format("ATTEMPTED TRANSFER FROM %s TO %s, BUT amount <= 0", name, receiver));
        }

        Account sender = data.getAccount(name);

        if (sender.getBalance() < amount) {
            throw new ArithmeticException(String.format("ATTEMPTED TRANSFER FROM %s TO %s, BUT amount < sender$getBalance()", name, receiver));
        }

        sender.removeFunds(amount);
        data.getAccount(receiver).addFunds(amount);
    }

    @Override
    public boolean changePassword(String name, String newPassword, String old) {
        Account account = AccountSavedData.get(server.overworld()).getAccount(name);

        if (account == null) {
            throw new NullPointerException("ATTEMPTED PASSWORD CHANGE BUT ACCOUNT EQUALS NULL");
        }

        if (passwordCorrect(name, old)) {
            account.changePassword(newPassword);
            ModLogger.info(String.format("PASSWORD CHANGE: %s", name));
            return true;
        }
        else {
            ModLogger.info(String.format("ATTEMPTED PASSWORD CHANGE ON %s, BUT PASSWORD INCORRECT", name));
            return false;
        }
    }

    @Override
    public boolean login(String name, String password, Player p) {
        Account account = AccountSavedData.get(server.overworld()).getAccount(name);

        if (account == null) {
            throw new IllegalArgumentException(String.format("ATTEMPTED LOGIN BY %s, BUT ACCOUNT EQUALS null", p.getStringUUID()));
        }

        if (passwordCorrect(name, password)) {
            p.getPersistentData().putString("liveconomy.account", name);
            account.addLoggedIn(p.getUUID());

            ModLogger.info(String.format("LOGIN: %s TO %s", p.getStringUUID(), account.getName()));
            return true;
        }
         else {
             return false;
        }

    }

    public void logout(Player p) {
        Account account = AccountSavedData.get(server.overworld()).getAccount(p.getPersistentData().getString("liveconomy.account"));

        if (account == null) {
            throw new IllegalArgumentException("ATTEMPTED LOGOUT BY %s, BUT ACCOUNT LOGGED IN NOT FOUND");
        }

        account.removeLoggedIn(p.getUUID());
        p.getPersistentData().putString("liveconomy.account", "");

        ModLogger.info(String.format("LOGOUT: %s FROM %s", p.getStringUUID(), account.getName()));
    }

    @Override
    public boolean passwordCorrect(String name, String password) {
        if (name == null || password == null || AccountSavedData.get(server.overworld()).getAccount(name) == null) {
            return false;
        }
        return AccountSavedData.get(server.overworld()).getAccount(name).getPassword().equals(password);
    }

}
