package com.cogworks.unorthodoxweapons.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockHoleEntity extends Entity {

    public BlockHoleEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
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
        super.tick();

        // 1. Spin rapidly in X and Y directions every tick
        // Adjust the multiplier values (e.g., 25.0f) to make it spin faster or slower
        this.setYRot(this.getYRot() + 25.0F);
        this.setXRot(this.getXRot() + 25.0F);

        // Keep rotation bounded between 0 and 360 degrees to prevent overflow
        if (this.getYRot() >= 360.0F) {
            this.setYRot(this.getYRot() - 360.0F);
        }
        if (this.getXRot() >= 360.0F) {
            this.setXRot(this.getXRot() - 360.0F);
        }

        // Run logic only on the server side
        if (!this.level().isClientSide()) {
            double searchRadius = 30.0D;
            double damageRadius = 5.0D;

            // Define the bounding box for the 30-block radius search
            AABB searchBox = this.getBoundingBox().inflate(searchRadius);
            List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    searchBox,
                    entity -> entity != (Entity) this && entity.isAlive()
            );

            for (LivingEntity target : nearbyEntities) {
                double distanceSq = this.distanceToSqr(target);

                // 2. Pull entities within the 30-block radius toward this entity
                if (distanceSq <= searchRadius * searchRadius && distanceSq > 0.1D) {
                    Vec3 pullDirection = this.position().subtract(target.position()).normalize();
                    double pullStrength = 0.15D;
                    target.setDeltaMovement(target.getDeltaMovement().add(pullDirection.scale(pullStrength)));
                    target.hurtMarked = true;
                }

                // 3. Deal 20 generic damage to entities within the 5-block radius
                if (distanceSq <= damageRadius * damageRadius) {
                    target.hurt(this.damageSources().generic(), 20.0F);
                }
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.@NotNull CompoundTag compound) {
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.@NotNull CompoundTag compound) {
    }
}