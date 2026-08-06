package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnorthodoxWeapons.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UNORTHODOX_WEAPONS_TAB = CREATIVE_MODE_TABS.register(
            "unorthodox_weapons_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.unorthodoxweapons"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.FIREBRAND.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.POISON_TRIDENT.get());
                        output.accept(ModItems.KICK_GUN.get());
                        output.accept(Util.make(new ItemStack(ModItems.FIREBRAND.get()), stack -> stack.enchant(parameters.holders().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT), 5)));
                    }).build());
}