package me.pajic.simple_trash_slot.mixin;

import me.pajic.simple_trash_slot.STSUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//? if >=26.3
import net.minecraft.world.entity.item.ItemEntity;

@Mixin(Inventory.class)
public abstract class InventoryMixin {

	@Shadow @Final public Player player;

	@Inject(
			method = "dropAll",
			at = @At("TAIL")
	)
	private void dropTrash(CallbackInfo ci) {
		ItemStack stack = STSUtil.getTrash(player).removeItemNoUpdate(0);
		if (stack.isEmpty() || EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) return;
		//? if >=26.3 {
		ItemEntity entity = player.createItemStackToDrop(stack, true, false);
		if (entity != null) player.level().addFreshEntity(entity);
		//?} else {
		/*player.drop(stack, true, false);
		*///?}
	}
}
