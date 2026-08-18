package com.cogworks.unorthodoxweapons.entities;

import com.cogworks.unorthodoxweapons.registry.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ThrownLonginusSpear extends ThrownTrident {

    private boolean dealtDamage;

    public ThrownLonginusSpear(EntityType<? extends ThrownTrident> type, Level level) {
        super(type, level);
        this.setItem(new ItemStack(ModItems.SPEAR_OF_LONGINUS.get()));
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        ItemStack trackedItem = this.getItem();
        return !trackedItem.isEmpty() ? trackedItem.copy() : new ItemStack(ModItems.SPEAR_OF_LONGINUS.get());
    }

    @Override
    public void playerTouch(@NotNull Player player) {
        if (!this.level().isClientSide && (this.inGround || this.isNoPhysics()) && this.ownedBy(player)) {
            ItemStack returnStack = this.getPickupItem();
            if (player.getInventory().add(returnStack)) {
                this.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.TRIDENT_RETURN, SoundSource.PLAYERS, 1.0F, 1.0F);
                this.discard();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.getOwner() instanceof Player player) {

            if (this.inGround || this.dealtDamage || this.isNoPhysics()) {
                this.setNoPhysics(true);

                Vec3 playerEyePos = player.getEyePosition(1.0F);
                Vec3 spearPos = this.position();
                Vec3 direction = playerEyePos.subtract(spearPos);

                double speed = 0.05D + (double)this.clientSideReturnTridentTickCount * 0.01D;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.05D).add(direction.normalize().scale(speed)));

                this.clientSideReturnTridentTickCount++;

                if (this.getBoundingBox().intersects(player.getBoundingBox())) {
                    this.clientSideReturnTridentTickCount = 0;
                    this.playerTouch(player);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        net.minecraft.world.entity.Entity target = result.getEntity();
        if (!this.level().isClientSide() && target instanceof Player player && player.isBlocking()) {
            if (this.isCritArrow()) {
                player.disableShield();
            }
        }
        super.onHitEntity(result);
        this.dealtDamage = true;
    }

    private ItemStack spearItemTracker = new ItemStack(ModItems.SPEAR_OF_LONGINUS.get());

    public void setItem(ItemStack stack) {
        ItemStack cleanCopy = stack.copy();
        cleanCopy.setCount(1);
        this.spearItemTracker = cleanCopy;
    }

    protected @NotNull ItemStack getItem() {
        return this.spearItemTracker != null ? this.spearItemTracker.copy() : new ItemStack(ModItems.SPEAR_OF_LONGINUS.get());
    }
}