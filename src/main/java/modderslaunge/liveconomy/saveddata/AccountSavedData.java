package modderslaunge.liveconomy.saveddata;

import modderslaunge.liveconomy.object.Account;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;

public class AccountSavedData extends SavedData {
    // STRING represents the name of the account
    private final HashMap<String, Account> ACCOUNTS =  new HashMap<String, Account>();

    public static AccountSavedData get(ServerLevel level) {
        return (AccountSavedData) level.getDataStorage().computeIfAbsent(AccountSavedData::new, AccountSavedData::new, "liveconomy.accounts");
    }

    public void addAccount(Account account) {
        ACCOUNTS.put(account.getName(), account);
    }

    public Account getAccount(String name) {
        return ACCOUNTS.get(name);
    }

    public void removeAccount(String name) {
        ACCOUNTS.remove(name);
    }

    public AccountSavedData() {

    }
    public AccountSavedData(CompoundTag tag) {

    }

    @Override
    public CompoundTag save(CompoundTag p_77763_) {
        return null;
    }
}
