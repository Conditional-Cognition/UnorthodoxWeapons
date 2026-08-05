package com.cogworks.unorthodoxweapons.events;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.cogworks.unorthodoxweapons.registry.*;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@EventBusSubscriber(modid = UnorthodoxWeapons.MODID)
public class KickGunEvents {

    private static final String KICK_TAG = "unorthodoxweapons_kick_projectile";

    @SubscribeEvent
    public static void onProjectileSpawn(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof Projectile projectile)) return;

        Entity owner = projectile.getOwner();
        if (!(owner instanceof LivingEntity livingOwner)) return;

        if (isHoldingKickGun(livingOwner)) {
            projectile.getPersistentData().putBoolean(KICK_TAG, true);
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        if (!projectile.getPersistentData().getBoolean(KICK_TAG)) return;

        if (event.getRayTraceResult() instanceof EntityHitResult entityHit
                && entityHit.getEntity() instanceof ServerPlayer targetPlayer) {
            targetPlayer.connection.disconnect(Component.literal("Kicked by a Kick Gun."));
        }
    }

    private static boolean isHoldingKickGun(LivingEntity entity) {
        ItemStack main = entity.getMainHandItem();
        ItemStack off = entity.getOffhandItem();
        return main.is(ModItems.KICK_GUN.get()) || off.is(ModItems.KICK_GUN.get());
    }
}