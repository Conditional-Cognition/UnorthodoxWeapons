package com.cogworks.unorthodoxweapons.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MochiHammerItem extends SwordItem {
    public MochiHammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        int randomNumber = new Random().nextInt(36);
        if (!attacker.level().isClientSide() && target instanceof Player targetPlayer) {
            ItemStack randomItem = targetPlayer.getInventory().getItem(randomNumber);
            Level level = targetPlayer.level();
            Vec3 pos = targetPlayer.position();

            ItemEntity itemEntity;
            if (!randomItem.isEmpty()) {
                itemEntity = new ItemEntity(level, pos.x, pos.y, pos.z, randomItem.copy());
                randomItem.shrink(randomItem.getCount());
            } else {
                itemEntity = new ItemEntity(level, pos.x, pos.y, pos.z, new ItemStack(Items.COBBLESTONE));
            }

            itemEntity.setPickUpDelay(40);
            level.addFreshEntity(itemEntity);
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
