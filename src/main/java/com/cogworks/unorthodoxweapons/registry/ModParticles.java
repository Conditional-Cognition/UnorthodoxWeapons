package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, UnorthodoxWeapons.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ADMIN_GUN_SHOOT_FLASH =
            PARTICLE_TYPES.register("admin_gun_shoot_flash", () -> new SimpleParticleType(false));
}