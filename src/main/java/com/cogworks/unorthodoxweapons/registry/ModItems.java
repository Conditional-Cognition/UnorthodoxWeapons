package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.cogworks.unorthodoxweapons.items.*;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnorthodoxWeapons.MODID);

    /* Jungle Spear
    public static final DeferredItem<PoisonTridentItem> POISON_TRIDENT = ITEMS.register(
            "jungle_spear",
            () -> new PoisonTridentItem(new Item.Properties().durability(250))); */
    // Admin Tool
    public static final DeferredItem<Item> ADMIN_TOOL =
            ITEMS.registerItem("admin_tool", AdminToolItem::new, new Item.Properties());

    // Firebrand
    public static final DeferredItem<FirebrandItem> FIREBRAND = ITEMS.register(
            "firebrand",
            () -> new FirebrandItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.NETHERITE, 8, -3.0f))));
    public static final DeferredHolder<Item, Item> FIREBRAND_COMPONENT =
            ITEMS.register("firebrand_component", () -> new FirebrandComponentItem(new Item.Properties()));
    // longunis
    public static final DeferredItem<LonginusSpearItem> SPEAR_OF_LONGINUS = ITEMS.register(
            "spear_of_longinus",
            () -> new LonginusSpearItem(new Item.Properties().stacksTo(1))
    );
    public static final DeferredItem<LonginusSpearItem> LANCE_OF_LONGINUS = ITEMS.register(
            "lance_of_longinus_model",
            () -> new LonginusSpearItem(new Item.Properties().stacksTo(1))
    );
}