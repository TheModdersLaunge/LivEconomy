package modderslaunge.liveconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import modderslaunge.liveconomy.api.AccountApi;
import modderslaunge.liveconomy.object.Account;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
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
            ctx.getSource().sendFailure(Component.literal("Command has to be sent by a player!"));
            return 0;
        }



        AccountApi api = new AccountApi(ctx.getSource().getServer());

        if (!api.getAccount(ctx.getArgument("name", String.class)).getCreator().equals(user.getUUID()) && !ctx.getSource().hasPermission(4)) {
            ctx.getSource().sendFailure(Component.literal("You do not own this account!"));
        }

        api.removeAccount(ctx.getArgument("name",String.class));

        ctx.getSource().sendSuccess(
                () -> Component.literal(String.format("Account %s has been removed",
                ctx.getArgument("name", String.class))), false);

        return 0;
    }

    private static int create(CommandContext<CommandSourceStack> ctx) {

        ServerPlayer user = ctx.getSource().getPlayer();

        if (user == null) {
            ctx.getSource().sendFailure(Component.literal("Command has to be sent by a player!"));
            return 0;
        }

        AccountApi api = new AccountApi(ctx.getSource().getServer());

        // already exists
        if (api.getAccount(ctx.getArgument("name", String.class)) != null) {
            ctx.getSource().sendFailure(Component.literal(String.format("Account %s already exists!", ctx.getArgument("name",String.class))));
            return 0;
        }

        // add account


        api.addAccount(
                new Account(ctx.getArgument("name",String.class),
                ctx.getArgument("password", String.class),
                user.getUUID()));

        ctx.getSource().sendSuccess(
                () -> Component.literal(String.format("Account %s has been created successfully!",
                        ctx.getArgument("name", String.class))),
                false);

        return 0;
    }





}
