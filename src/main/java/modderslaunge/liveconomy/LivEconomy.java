package modderslaunge.liveconomy;

import com.mojang.logging.LogUtils;
import modderslaunge.liveconomy.core.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LivEconomy.MODID)
public class LivEconomy {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "liveconomy";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public LivEconomy() {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        ModItems.ITEMS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "liveconomy-server.toml");
    }
}
