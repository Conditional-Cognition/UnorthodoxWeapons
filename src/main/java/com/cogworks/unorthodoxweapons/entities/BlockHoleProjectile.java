package com.cogworks.unorthodoxweapons.entities;

import com.cogworks.unorthodoxweapons.registry.ModEntities;
import com.cogworks.unorthodoxweapons.registry.ModItems;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BlockHoleProjectile extends ThrowableItemProjectile {
    private boolean hasHit = false;
    private int particleWaitTicks = 0;

    public BlockHoleProjectile(EntityType<? extends BlockHoleProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.BLOCK_HOLE_PROJECTILE_MODEL.get();
    }

    @Override
    public void tick() {
        if (this.hasHit) {
            this.setDeltaMovement(Vec3.ZERO);

            if (!this.level().isClientSide()) {
                this.particleWaitTicks++;

                if (this.particleWaitTicks >= 20) {
                    BlockHoleEntity hole = new BlockHoleEntity(ModEntities.BLOCK_HOLE.get(), this.level());
                    hole.setPos(this.getX(), this.getY(), this.getZ());
                    hole.setMaxLifeInSeconds(10.0F);
                    this.level().addFreshEntity(hole);

                    this.discard();
                }
            }
            return;
        }

        super.tick();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        if (!this.hasHit) {
            this.hasHit = true;
            this.noPhysics = true;
            this.setPos(result.getLocation().x, result.getLocation().y, result.getLocation().z);

            if (this.level().isClientSide()) {
                ParticleType<?> rawType = BuiltInRegistries.PARTICLE_TYPE.get(
                        ResourceLocation.fromNamespaceAndPath(
                                "unorthodox_weapons",
                                "admin_gun_shoot_flash")
                );
                if (rawType instanceof net.minecraft.core.particles.ParticleOptions flashParticle) {
                    this.level().addParticle(flashParticle,
                            this.getX(), this.getY(), this.getZ(),
                            0.0D, 0.0D, 0.0D);
                }
            }

        }
    }
}
