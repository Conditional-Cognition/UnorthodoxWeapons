package com.cogworks.unorthodoxweapons.items;

import com.cogworks.unorthodoxweapons.entities.PoisonThrownTrident;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PoisonTridentItem extends TridentItem {

    private static final int POISON_DURATION_TICKS = 100; // 5 seconds
    private static final int POISON_AMPLIFIER = 2;
    private static final int MIN_DRAW_DURATION = 10;

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

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeCharged) {
        if (!(livingEntity instanceof Player player)) {
            return;
        }

        int chargeTicks = this.getUseDuration(stack, livingEntity) - timeCharged;
        if (chargeTicks < MIN_DRAW_DURATION) {
            return;
        }

        if (level instanceof ServerLevel serverLevel) {
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

            PoisonThrownTrident trident = new PoisonThrownTrident(level, player, stack);
            trident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 2.5f, 1.0f);
            serverLevel.addFreshEntity(trident);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0f, 1.0f);

            if (!player.getAbilities().instabuild) {
                player.getInventory().removeItem(stack);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.jungle_spear.desc"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}