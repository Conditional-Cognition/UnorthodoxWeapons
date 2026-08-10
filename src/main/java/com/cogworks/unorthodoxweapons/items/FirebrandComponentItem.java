package com.cogworks.unorthodoxweapons.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FirebrandComponentItem extends Item {
    public FirebrandComponentItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        target.igniteForSeconds(4);
        return super.hurtEnemy(stack, target, attacker);
    }
}