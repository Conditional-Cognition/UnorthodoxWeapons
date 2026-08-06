package com.cogworks.unorthodoxweapons.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class PoisonThrownTrident extends ThrownTrident {

    private static final int POISON_DURATION_TICKS = 100; // 5 seconds
    private static final int POISON_AMPLIFIER = 2;

    public PoisonThrownTrident(EntityType<? extends ThrownTrident> type, Level level) {
        super(type, level);
    }

    public PoisonThrownTrident(Level level, Player player, ItemStack stack) {
        super(level, player, stack);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level() instanceof ServerLevel && result.getEntity() instanceof LivingEntity target) {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, POISON_DURATION_TICKS, POISON_AMPLIFIER), this.getOwner());
        }
    }
}