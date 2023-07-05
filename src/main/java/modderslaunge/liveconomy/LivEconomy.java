package modderslaunge.liveconomy;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LivEconomy.MODID)
public class LivEconomy {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "liveconomy";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

}
