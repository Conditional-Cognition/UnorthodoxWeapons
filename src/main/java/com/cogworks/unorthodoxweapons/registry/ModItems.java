package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.cogworks.unorthodoxweapons.items.FirebrandItem;
import com.cogworks.unorthodoxweapons.items.KickGunItem;
import com.cogworks.unorthodoxweapons.items.PoisonTridentItem;

import net.minecraft.world.item.AxeItem;
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

    // Firebrand
    public static final DeferredItem<FirebrandItem> FIREBRAND = ITEMS.register(
            "firebrand",
            () -> new FirebrandItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.NETHERITE, 12, -3.0f))));

}