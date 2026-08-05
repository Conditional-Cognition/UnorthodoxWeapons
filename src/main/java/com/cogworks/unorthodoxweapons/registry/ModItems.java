package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.cogworks.unorthodoxweapons.items.KickGunItem;
import com.cogworks.unorthodoxweapons.items.PoisonTridentItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnorthodoxWeapons.MODID);

    // Jungle Spear
    public static final DeferredItem<PoisonTridentItem> POISON_TRIDENT = ITEMS.register(
            "jungle_spear",
            () -> new PoisonTridentItem(new Item.Properties().durability(250)));

    // Kick Gun
    public static final DeferredItem<KickGunItem> KICK_GUN = ITEMS.register(
            "kick_gun",
            () -> new KickGunItem(new Item.Properties().durability(465)));

    // Cleavers
    public static final DeferredItem<SwordItem> WOODEN_CLEAVER = ITEMS.register(
            "wooden_cleaver",
            () -> new SwordItem(Tiers.WOOD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.WOOD, 3, -2.4f))));

    public static final DeferredItem<SwordItem> STONE_CLEAVER = ITEMS.register(
            "stone_cleaver",
            () -> new SwordItem(Tiers.STONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.STONE, 3, -2.4f))));

    public static final DeferredItem<SwordItem> IRON_CLEAVER = ITEMS.register(
            "iron_cleaver",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4f))));

    public static final DeferredItem<SwordItem> GOLD_CLEAVER = ITEMS.register(
            "gold_cleaver",
            () -> new SwordItem(Tiers.GOLD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.GOLD, 3, -2.4f))));

    public static final DeferredItem<SwordItem> DIAMOND_CLEAVER = ITEMS.register(
            "diamond_cleaver",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4f))));

    public static final DeferredItem<SwordItem> NETHERITE_CLEAVER = ITEMS.register(
            "netherite_cleaver",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 3, -2.4f))
                    .fireResistant()));
}