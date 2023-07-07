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
        AccountApi api = new AccountApi(ctx.getSource().getServer());

        if (ctx.getSource().getPlayer().equals(null)) {
            ModLogger.playerError("Command has to be sent by a user!",ctx.getSource().getPlayer());
            return 0;
        }

        if (api.getAccount(ctx.getArgument("name",String.class)).equals(null)) {

                    ModLogger.playerError(String.format("Account %s does not exist! Please use /account create [username] [password] to make it!"
                            ,ctx.getArgument("name",String.class)), ctx.getSource().getPlayer());
            return 0;
        }

        if (api.getAccount(ctx.getArgument("name",String.class)).getPassword().equals(ctx.getArgument("password",String.class)) && !ctx.getSource().hasPermission(4)) {
            ModLogger.playerError("Password is incorrect!",ctx.getSource().getPlayer());
            return 0;
        }

        ModLogger.playerInfo("Successfully logged in!",ctx.getSource().getPlayer());


        return 0;
    }
}
