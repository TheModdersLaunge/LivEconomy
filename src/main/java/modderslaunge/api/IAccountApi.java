package modderslaunge.api;

import modderslaunge.liveconomy.object.Account;

public interface IAccountApi {
    void addAccount(Account account);

    Account getAccount(String name);

    void removeAccount(String name); // by  creator or admin

    void login(String name, String password);

    boolean passwordCorrect(String name, String password);
  
    void transfer(String name, long amount, String receiver);
}
