package com.cogworks.unorthodoxweapons.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockHoleEntity extends Entity {

    private int lifeTicks = 0;
    private int maxLifeTicks = 100;

    public BlockHoleEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public void setMaxLifeInSeconds(float seconds) {
        this.maxLifeTicks = (int) (seconds * 20.0F);
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.@NotNull Builder builder) {
        
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide()) {
            this.lifeTicks++;
            if (this.lifeTicks >= this.maxLifeTicks) {
                this.discard();
                return;
            }
        }

        super.tick();

        this.setYRot(this.getYRot() + 25.0F);
        this.setXRot(this.getXRot() + 25.0F);

        if (this.getYRot() >= 360.0F) {
            this.setYRot(this.getYRot() - 360.0F);
        }
        if (this.getXRot() >= 360.0F) {
            this.setXRot(this.getXRot() - 360.0F);
        }

        if (!this.level().isClientSide()) {
            double searchRadius = 30.0D;
            double damageRadius = 5.0D;

            AABB searchBox = this.getBoundingBox().inflate(searchRadius);

            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    searchBox,
                    entity -> entity.getId() != this.getId()
                            && entity.isAlive()
                            && !(entity instanceof Player p && (p.isSpectator() || p.isCreative()))
            );

            for (LivingEntity target : nearbyEntities) {
                double distanceSq = this.distanceToSqr(target);

                if (distanceSq <= searchRadius * searchRadius && distanceSq > 0.1D) {
                    Vec3 pullDirection = this.position().subtract(target.position()).normalize();
                    double pullStrength = 0.15D;
                    target.setDeltaMovement(target.getDeltaMovement().add(pullDirection.scale(pullStrength).add(0,0.001,0)));
                    target.hurtMarked = true;
                }

                if (distanceSq <= damageRadius * damageRadius) {
                    target.hurt(this.damageSources().generic(), 20.0F);
                }
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.@NotNull CompoundTag compound) {
        if (compound.contains("LifeTicks")) {
            this.lifeTicks = compound.getInt("LifeTicks");
        }
        if (compound.contains("MaxLifeTicks")) {
            this.maxLifeTicks = compound.getInt("MaxLifeTicks");
        }
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.@NotNull CompoundTag compound) {
        compound.putInt("LifeTicks", this.lifeTicks);
        compound.putInt("MaxLifeTicks", this.maxLifeTicks);
    }

    /*
    EXAMPLE USAGE (by Cndtnl_Cognition)

    BlockHoleEntity hole = new BlockHoleEntity(ModEntities.BLOCK_HOLE.get(), level);
    hole.setMaxLifeInSeconds(10.0F);
    level.addFreshEntity(hole);
     */
}