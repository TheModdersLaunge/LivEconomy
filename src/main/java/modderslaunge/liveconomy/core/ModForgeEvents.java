package modderslaunge.liveconomy.core;

import modderslaunge.liveconomy.LivEconomy;
import modderslaunge.liveconomy.commands.AccountCommand;
import modderslaunge.liveconomy.commands.LoginCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= LivEconomy.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)

public class ModForgeEvents {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        AccountCommand.register(event.getDispatcher());
        LoginCommand.register(event.getDispatcher());
    }
}
