package com.cogworks.unorthodoxweapons.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirebrandItem extends AxeItem {
    public FirebrandItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
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

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.firebrand.desc"));
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.firebrand.desc2"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        target.igniteForSeconds(20);
        return super.hurtEnemy(stack, target, attacker);
    }
}
