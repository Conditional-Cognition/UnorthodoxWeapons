package com.cogworks.unorthodoxweapons.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import org.jetbrains.annotations.NotNull;

public class PoisonTridentItem extends TridentItem {

    private static final int POISON_DURATION_TICKS = 100; // 5 seconds
    private static final int POISON_AMPLIFIER = 0;

    public PoisonTridentItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, POISON_DURATION_TICKS, POISON_AMPLIFIER), attacker);
        }
        return result;
    }
}