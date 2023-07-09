package modderslaunge.liveconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import modderslaunge.liveconomy.api.AccountApi;
import modderslaunge.liveconomy.api.ModLogger;
import modderslaunge.liveconomy.object.Account;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class AccountCommand {
    public static void register(CommandDispatcher<CommandSourceStack> ctx) {
        ctx.register(Commands.literal("account")
                .then(Commands.literal("create")
                    .then(Commands.argument("name", StringArgumentType.word())
                        .then(Commands.argument("password",StringArgumentType.word()).executes(AccountCommand::create)))));


        ctx.register(Commands.literal("account")
                .then(Commands.literal("remove")
                        .then(Commands.argument("name", StringArgumentType.word()).executes(AccountCommand::remove))));

        ctx.register(Commands.literal("account").then(Commands.literal("changepassword")
                        .then(Commands.argument("name",StringArgumentType.word())
                .then(Commands.argument("old",StringArgumentType.word())
                        .then(Commands.argument("new",StringArgumentType.word()).executes(AccountCommand::changepassword))))));

    }

    private static int changepassword(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer user = ctx.getSource().getPlayer();
        // Check if sender is null.
        if (user == null) {
            ModLogger.ctxError("Command has to be sent by a player",ctx);
            return 0;
        }

        AccountApi api = new AccountApi(ctx.getSource().getServer());

        // Checks if Account exists

        if (api.getAccount(ctx.getArgument("name",String.class)) == null) {
            ModLogger.playerError(String.format("Account %s does not exists!",ctx.getArgument("name",String.class)),ctx.getSource().getPlayer());
            return 0;
        }

        // Checks if old password matches at all with the account
        // If it doesn't but they have OP then they can change it no matter what

        if (!api.getAccount(ctx.getArgument("name",String.class)).getPassword()
                .equals(ctx.getArgument("old",String.class)) && !ctx.getSource().hasPermission(4)) {
            ModLogger.playerError("Old password is incorrect!",ctx.getSource().getPlayer());
            return 0;
        }

        // Checks if new password equals to the old one
        if (api.getAccount(ctx.getArgument("name",String.class)).getPassword()
                .equals(ctx.getArgument("new",String.class))) {
            ModLogger.playerError("Cannot change password to the previous one!",ctx.getSource().getPlayer());
            return 0;
        }

        // Changes password
        new Account(ctx.getArgument("name",String.class),ctx.getArgument("new",String.class),ctx.getSource().getPlayer().getUUID());
        ModLogger.playerInfo("Password has been successfully reset!",ctx.getSource().getPlayer());

        return 0;
    }


    private static int remove(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer user = ctx.getSource().getPlayer();

        if (user == null) {
            ModLogger.ctxError("Command has to be sent by a player",ctx);
            return 0;
        }


        AccountApi api = new AccountApi(ctx.getSource().getServer());

        if (api.getAccount(ctx.getArgument("name",String.class)) == null) {
            ModLogger.playerError(String.format("Account %s does not exist!",ctx.getArgument("name",String.class)),ctx.getSource().getPlayer());
            return 0;
        }

        if (!api.getAccount(ctx.getArgument("name", String.class)).getCreator().equals(user.getUUID()) && !ctx.getSource().hasPermission(4)) {
            ModLogger.playerError("You do not own this account!",ctx.getSource().getPlayer());
            return 0;
        }

        api.removeAccount(ctx.getArgument("name",String.class));

        ModLogger.playerInfo(String.format("Account %s has been removed",ctx.getArgument("name",String.class)),ctx.getSource().getPlayer());

        return 0;
    }

    private static int create(CommandContext<CommandSourceStack> ctx) {

        ServerPlayer user = ctx.getSource().getPlayer();

        if (user == null) {
            ModLogger.ctxError("Command has to be sent by a player",ctx);
            return 0;
        }

        AccountApi api = new AccountApi(ctx.getSource().getServer());

        // already exists
        if (api.getAccount(ctx.getArgument("name", String.class)) != null) {
            ModLogger.playerError(String.format("Account %s already exists!",ctx.getArgument("name",String.class)),ctx.getSource().getPlayer());
            return 0;
        }

        // add account


        api.addAccount(
                new Account(ctx.getArgument("name",String.class),
                ctx.getArgument("password", String.class),
                user.getUUID()));

        ModLogger.playerInfo(String.format("Account %s has been successfully created!",ctx.getArgument("name",String.class)),ctx.getSource().getPlayer());


        return 0;
    }





}
