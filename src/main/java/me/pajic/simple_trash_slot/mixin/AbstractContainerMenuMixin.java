package me.pajic.simple_trash_slot.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.simple_trash_slot.STSUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    @Definition(id = "setCarried", method = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setCarried(Lnet/minecraft/world/item/ItemStack;)V")
    @Definition(id = "clicked", local = @Local(type = ItemStack.class, name = "clicked"))
    @Expression("this.setCarried(@(clicked))")
    @ModifyExpressionValue(
            method = "doClick",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private ItemStack trashDifferentItem(
			ItemStack original,
			@Local(name = "slotIndex", argsOnly = true) int slotIndex,
			@Local(name = "player", argsOnly = true) Player player
	) {
		if (slotIndex == STSUtil.slotId) {
			if (!original.isEmpty()) STSUtil.playSound(player);
			return ItemStack.EMPTY;
		}
        return original;
    }

	@Definition(id = "setCarried", method = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setCarried(Lnet/minecraft/world/item/ItemStack;)V")
	@Definition(id = "safeInsert", method = "Lnet/minecraft/world/inventory/Slot;safeInsert(Lnet/minecraft/world/item/ItemStack;I)Lnet/minecraft/world/item/ItemStack;")
	@Definition(id = "carried", local = @Local(type = ItemStack.class, name = "carried"))
	@Definition(id = "amount", local = @Local(type = int.class, name = "amount"))
	@Expression("this.setCarried(@(?.safeInsert(carried, amount)))")
	@ModifyExpressionValue(
			method = "doClick",
			at = @At(
					value = "MIXINEXTRAS:EXPRESSION",
					ordinal = 1
			)
	)
	private ItemStack trashSameItem(
			ItemStack original,
			@Local(name = "slotIndex", argsOnly = true) int slotIndex,
			@Local(name = "player", argsOnly = true) Player player,
			@Local(name = "carried") ItemStack carried,
			@Local(name = "clickAction") ClickAction clickAction
	) {
		if (slotIndex == STSUtil.slotId && carried.count() == original.count() && clickAction == ClickAction.PRIMARY) {
			if (!original.isEmpty()) STSUtil.playSound(player);
			return ItemStack.EMPTY;
		}
		return original;
	}
}
