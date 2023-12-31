package modderslaunge.liveconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import modderslaunge.liveconomy.api.AccountApi;
import modderslaunge.liveconomy.api.ModLogger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class LoginCommand {
    public static void register(CommandDispatcher<CommandSourceStack> ctx) {
        ctx.register(Commands.literal("login")
                .then(Commands.argument("name", StringArgumentType.word())
                        .then(Commands.argument("password",StringArgumentType.word()).executes(LoginCommand::login))));

    }

    private static int login(CommandContext<CommandSourceStack> ctx) {
        // Make a new instance of api
        AccountApi api = new AccountApi(ctx.getSource().getServer());

        if (ctx.getSource().getPlayer() == null) {
            // Send PlayerError through ModLogger that tells the user that command has to be sent by a user.
            ModLogger.ctxError("Command has to be sent by a player",ctx);
            return 0;
        }

        if (api.getAccount(ctx.getArgument("name",String.class)) == null) {
            // Send PlayerError through ModLogger that account does not exist.

            ModLogger.playerError(String.format("Account %s does not exist! Please use /account create [username] [password] to make it!"
                            ,ctx.getArgument("name",String.class)), ctx.getSource().getPlayer());
            return 0;
        }

        // password incorrect and does not have admin privileges
        if (!api.passwordCorrect(ctx.getArgument("name", String.class), ctx.getArgument("password", String.class)) && !ctx.getSource().hasPermission(4)) {
            ModLogger.playerError("Password is incorrect!",ctx.getSource().getPlayer());
            return 0;
        }
        // Api Login :)
        api.login(ctx.getArgument("name",String.class),ctx.getArgument("password",String.class),ctx.getSource().getPlayer());

        // Call ModLogger to tell player that they are successfully logged in.
        ModLogger.playerInfo("Successfully logged in!",ctx.getSource().getPlayer());

        // return 0
        return 0;
    }
}
