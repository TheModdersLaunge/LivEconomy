package modderslaunge.api;

import modderslaunge.liveconomy.object.Account;

public interface IAccountApi {
    void addAccount(Account account);

    Account getAccount(String name);

    void removeAccount(String name); // by  creator or admin

    void transfer(String name, long amount, String receiver);
}
