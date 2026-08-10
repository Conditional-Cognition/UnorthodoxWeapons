package com.cogworks.unorthodoxweapons.items.admin;

import com.cogworks.unorthodoxweapons.UnorthodoxWeapons;
import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AdminModeAttachment {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, UnorthodoxWeapons.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<AdminGunMode>> ADMIN_GUN_MODE =
            ATTACHMENT_TYPES.register("admin_gun_mode", () ->
                    AttachmentType.builder(() -> AdminGunMode.KILL_KICK)
                            .serialize(Codec.STRING.xmap(AdminGunMode::valueOf, Enum::name))
                            .build());

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}