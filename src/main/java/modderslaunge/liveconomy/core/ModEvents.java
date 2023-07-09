package modderslaunge.liveconomy.core;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import static modderslaunge.liveconomy.LivEconomy.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ModEvents {
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.level().isClientSide) {
            return;
        }
    }
}
