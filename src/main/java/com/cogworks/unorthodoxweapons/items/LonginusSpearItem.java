package com.cogworks.unorthodoxweapons.items;

import com.cogworks.unorthodoxweapons.entities.ThrownLonginusSpear;
import com.cogworks.unorthodoxweapons.registry.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LonginusSpearItem extends TridentItem {
    public LonginusSpearItem(Properties properties) {
        super(properties);
    }
    @Override
    public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player) {
        super.onCraftedBy(stack, level, player);
        applyPermanentLoyalty(stack, level);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!level.isClientSide()) {
            ItemEnchantments currentEnchants = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            if (currentEnchants.getLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOYALTY)) < 1) {
                applyPermanentLoyalty(stack, level);
            }
        }
    }

    private void applyPermanentLoyalty(ItemStack stack, Level level) {
        Holder<Enchantment> loyaltyHolder = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(Enchantments.LOYALTY);

        ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(
                stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
        );

        enchantments.set(loyaltyHolder, 1);
        stack.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable());
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.SPEAR;
    }
    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity livingEntity) {
        return 72000;
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeCharged) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(stack, livingEntity) - timeCharged;
            if (i >= 10) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(livingEntity.getUsedItemHand()));

                    ThrownLonginusSpear tridentEntity = new ThrownLonginusSpear(ModEntities.LONGINUS_LANCE.get(), level);

                    tridentEntity.setPos(player.getX(), player.getEyeY() - 0.1D, player.getZ());
                    tridentEntity.setItem(stack.copy());
                    tridentEntity.setOwner(player);
                    tridentEntity.pickup = AbstractArrow.Pickup.ALLOWED;

                    tridentEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

                    if (player.getAbilities().instabuild) {
                        tridentEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(tridentEntity);

                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.TRIDENT_THROW.value(),
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F
                    );

                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && target instanceof Player player && player.isBlocking()) {
            if (attacker instanceof Player playerAttacker && playerAttacker.getDeltaMovement().y < 0.0 && !playerAttacker.onGround()) {
                player.disableShield();
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.spear_of_longinus.desc"));
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.spear_of_longinus.desc2"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
