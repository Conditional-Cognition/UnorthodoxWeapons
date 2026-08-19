package com.cogworks.unorthodoxweapons.items;

import com.cogworks.unorthodoxweapons.entities.BlockHoleProjectile;
import com.cogworks.unorthodoxweapons.registry.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockHoleLauncherItem extends Item {

    public BlockHoleLauncherItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!level.isClientSide()) {
            BlockHoleProjectile projectile = new BlockHoleProjectile(ModEntities.BLOCK_HOLE_PROJECTILE.get(), level);
            
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            projectile.setPos(player.getEyePosition());
            level.addFreshEntity(projectile);

            if (!level.isClientSide() && !player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.block_hole_launcher.desc"));
        tooltipComponents.add(Component.translatable("item.unorthodoxweapons.block_hole_launcher.desc2"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
