package com.cogworks.unorthodoxweapons.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FirebrandItem extends AxeItem {
    public FirebrandItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            Vec3 lookAngle = player.getLookAngle();

            SmallFireball fireball = new SmallFireball(
                    level,
                    player.getX(),
                    player.getEyeY() - 0.1,
                    player.getZ(),
                    lookAngle
            );

            fireball.setOwner(player);
            level.addFreshEntity(fireball);

            player.getCooldowns().addCooldown(this, 40);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

}
