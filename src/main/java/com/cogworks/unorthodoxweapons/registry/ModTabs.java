package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnorthodoxWeapons.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UNORTHODOX_WEAPONS_TAB = CREATIVE_MODE_TABS.register(
            "unorthodox_weapons_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.unorthodoxweapons"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.DIAMOND_CLEAVER.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.POISON_TRIDENT.get());
                        output.accept(ModItems.KICK_GUN.get());
                        output.accept(ModItems.WOODEN_CLEAVER.get());
                        output.accept(ModItems.STONE_CLEAVER.get());
                        output.accept(ModItems.IRON_CLEAVER.get());
                        output.accept(ModItems.GOLD_CLEAVER.get());
                        output.accept(ModItems.DIAMOND_CLEAVER.get());
                        output.accept(ModItems.NETHERITE_CLEAVER.get());
                    }).build());
}