package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.cogworks.unorthodoxweapons.items.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnorthodoxWeapons.MODID);

    /* Jungle Spear
    public static final DeferredItem<PoisonTridentItem> POISON_TRIDENT = ITEMS.register(
            "jungle_spear",
            () -> new PoisonTridentItem(new Item.Properties().durability(250))); */
    // Admin Tool (dmbliz)
    public static final DeferredItem<Item> ADMIN_TOOL =
            ITEMS.registerItem("admin_tool", AdminToolItem::new, new Item.Properties());

    // Firebrand (NinjaBoy1840)
    public static final DeferredItem<FirebrandItem> FIREBRAND = ITEMS.register(
            "firebrand",
            () -> new FirebrandItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.NETHERITE, 8, -3.0f))));
    public static final DeferredHolder<Item, Item> FIREBRAND_COMPONENT =
            ITEMS.register("firebrand_component", () -> new FirebrandComponentItem(new Item.Properties()));
    // longunis (monkie55)
    public static final DeferredItem<LonginusSpearItem> SPEAR_OF_LONGINUS = ITEMS.register(
            "spear_of_longinus",
            () -> new LonginusSpearItem(new Item.Properties()
                    .stacksTo(1)
                    .attributes(TridentItem.createAttributes())
            )
    );
    public static final DeferredItem<UnobtainableItem> LANCE_OF_LONGINUS = ITEMS.register(
            "lance_of_longinus_model",
            () -> new UnobtainableItem(new Item.Properties().stacksTo(1))
    );
    // mochi_hammer (opiuppenguin)
    public static final DeferredItem<MochiHammerItem> MOCHI_HAMMER = ITEMS.register(
            "mochi_hammer",
            () -> new MochiHammerItem(Tiers.WOOD,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(Tiers.WOOD, 5.0F, -2.4F))
                            .stacksTo(1)
                            .durability(100)
            )
    );
    // block hole (Megamanta_365)
    public static final DeferredItem<UnobtainableItem> BLOCK_HOLE_MODEL = ITEMS.register(
            "block_hole_model",
            () -> new UnobtainableItem(new Item.Properties().stacksTo(1))
    );
    public static final DeferredItem<UnobtainableItem> BLOCK_HOLE_PROJECTILE_MODEL = ITEMS.register(
            "block_hole_projectile_model",
            () -> new UnobtainableItem(new Item.Properties().stacksTo(1))
    );
    public static final DeferredItem<BlockHoleLauncherItem> BLOCK_HOLE_LAUNCHER = ITEMS.register(
            "block_hole_launcher",
            () -> new BlockHoleLauncherItem(new Item.Properties().stacksTo(1))
    );
    // Gasoline (Cndtnl_Cognition)
    // new mod, gonna be required lmao
}