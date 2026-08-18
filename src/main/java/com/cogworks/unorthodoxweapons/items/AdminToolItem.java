package com.cogworks.unorthodoxweapons.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminToolItem extends Item {

    private static final ResourceLocation REACH_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("unorthodoxweapons", "admin_tool_reach");

    public AdminToolItem(Properties properties) {
        super(properties.component(
                DataComponents.ATTRIBUTE_MODIFIERS,
                ItemAttributeModifiers.builder()
                        .add(Attributes.ENTITY_INTERACTION_RANGE,
                                new AttributeModifier(REACH_MODIFIER_ID, 4096.0, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.HAND)
                        .add(Attributes.BLOCK_INTERACTION_RANGE,
                                new AttributeModifier(REACH_MODIFIER_ID, 4096.0, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.HAND)
                        .build()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.admin_tool.desc"));
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.admin_tool.desc2"));
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.admin_tool.desc3"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}