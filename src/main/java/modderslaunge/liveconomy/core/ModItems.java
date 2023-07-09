package modderslaunge.liveconomy.core;

import modderslaunge.liveconomy.LivEconomy;
import modderslaunge.liveconomy.item.CardItem;
import modderslaunge.liveconomy.item.WalletItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LivEconomy.MODID);

    public static final RegistryObject<CardItem> CARD = ITEMS.register("debit_card", () -> new CardItem(new Item.Properties().stacksTo(1).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<WalletItem> WALLET = ITEMS.register("wallet", () -> new WalletItem(new Item.Properties().stacksTo(1).setNoRepair().rarity(Rarity.EPIC)));

}
