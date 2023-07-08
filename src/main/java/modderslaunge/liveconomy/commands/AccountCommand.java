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
