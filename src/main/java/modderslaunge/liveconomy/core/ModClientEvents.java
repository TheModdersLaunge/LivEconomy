package modderslaunge.liveconomy.core;

import modderslaunge.liveconomy.LivEconomy;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = LivEconomy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(ModItems.WALLET.get(), new ResourceLocation(LivEconomy.MODID, "empty"), (stack, level, entity, entityId) -> stack.getOrCreateTag().getLong("liveconomy.balance") > 0 ? 0.0f : 1.0f));
    }
}
