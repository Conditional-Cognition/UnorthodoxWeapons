package com.cogworks.unorthodoxweapons.registry;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
//import com.cogworks.unorthodoxweapons.entities.PoisonThrownTrident;

import com.cogworks.unorthodoxweapons.entities.ThrownLonginusSpear;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType; /*
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder; */
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, UnorthodoxWeapons.MODID);
    /*
    public static final DeferredHolder<EntityType<?>, EntityType<PoisonThrownTrident>> POISON_THROWN_TRIDENT =
            ENTITY_TYPES.register("poison_thrown_trident", id -> EntityType.Builder.<PoisonThrownTrident>of(PoisonThrownTrident::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(id.getPath())); */
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownLonginusSpear>> LONGINUS_LANCE =
            ENTITY_TYPES.register("thrown_longinus_spear", id -> EntityType.Builder.of(ThrownLonginusSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(id.getPath()));
}