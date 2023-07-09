package modderslaunge.liveconomy.api;

import com.mojang.brigadier.context.CommandContext;
import modderslaunge.liveconomy.LivEconomy;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ModLogger {

    public static void info(String message) {
        LivEconomy.LOGGER.info(String.format("[LivEconomy] %s", message));
    }

    public static void error(String message) {
        LivEconomy.LOGGER.error(String.format("[LivEconomy] %s", message));
    }

    public static void playerInfo(String message, Player p) {
        p.sendSystemMessage(
                Component.literal("[").withStyle(ChatFormatting.RED)
                        .append("LivEconomy").withStyle(ChatFormatting.AQUA)
                        .append("] ").withStyle(ChatFormatting.RED)
                        .append(message).withStyle(ChatFormatting.AQUA));
    }

    public static void playerError(String message, Player p) {
        p.sendSystemMessage(Component.literal("[LivEconomy] ").withStyle(ChatFormatting.RED)
                .append(message).withStyle(ChatFormatting.RED));
    }

    public static void ctxError(String message, CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().sendFailure(Component.literal("[LivEconomy] ").withStyle(ChatFormatting.RED).append(message).withStyle(ChatFormatting.RED));
    }

    public static void ctxSuccess(String message, CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().sendSuccess(() ->
                Component.literal("[").withStyle(ChatFormatting.RED)
                        .append("LivEconomy").withStyle(ChatFormatting.AQUA)
                        .append("] ").withStyle(ChatFormatting.RED)
                        .append(message).withStyle(ChatFormatting.AQUA),false);
    }
}
