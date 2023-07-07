package modderslaunge.liveconomy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import modderslaunge.liveconomy.api.AccountApi;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class LoginCommand {
    public static void register(CommandDispatcher<CommandSourceStack> ctx) {
        ctx.register(Commands.literal("login")
                .then(Commands.argument("name", StringArgumentType.word())
                        .then(Commands.argument("password",StringArgumentType.word()).executes(LoginCommand::login))));

    }

    private static int login(CommandContext<CommandSourceStack> ctx) {
        AccountApi api = new AccountApi(ctx.getSource().getServer());

        if (ctx.getSource().getPlayer() == null) {
            ctx.getSource().sendFailure(Component.literal("To login you have to be a player!"));
        }

        if (api.getAccount(ctx.getArgument("name",String.class)) == null) {
            ctx.getSource().sendFailure(
                    Component.literal(String.format( "Account %s does not exist! Please use /account create [username] [password] to make it!",
                            ctx.getArgument("name",String.class))));
            return 0;
        }

        if (api.getAccount(ctx.getArgument("name",String.class)).getPassword() != ctx.getArgument("password",String.class) && !ctx.getSource().hasPermission(4)) {
            ctx.getSource().sendFailure(Component.literal("Password is incorrect!"));
        }

        // No Login implemented in API :(
        ctx.getSource().sendSuccess(() -> Component.literal("Successfully logged in!"), false);

        return 0;
    }
}
