package com.cogworks.unorthodoxweapons.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminToolItem extends Item {
    public AdminToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        // Use Component.translatable() to point to your localization key
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.admin_tool.desc"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
