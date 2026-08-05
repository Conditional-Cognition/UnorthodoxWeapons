package com.cogworks.unorthodoxweapons;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = UnorthodoxWeapons.MODID, dist = Dist.CLIENT)
public class UnorthodoxWeaponsClient {
    public UnorthodoxWeaponsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}