package modderslaunge.liveconomy;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> CARD_EXPIRES_AFTER;

    static {
        CARD_EXPIRES_AFTER = BUILDER.comment("Amount of days after cards expire").define("card_expiry", 30);
        SPEC = BUILDER.build();
    }
}
