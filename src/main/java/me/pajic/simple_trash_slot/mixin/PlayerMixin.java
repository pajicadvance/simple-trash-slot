package me.pajic.simple_trash_slot.mixin;

import me.pajic.simple_trash_slot.TrashHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin implements TrashHolder {

	@Unique
	private static final String TRASH_KEY = "simple_trash_slot:trash";

	@Unique
	private SimpleContainer trash;

	@Override
	public SimpleContainer sts$getTrash() {
		if (trash == null) trash = new SimpleContainer(1);
		return trash;
	}

	@Inject(
			method = "addAdditionalSaveData",
			at = @At("TAIL")
	)
	private void saveTrash(ValueOutput output, CallbackInfo ci) {
		ItemStack stack = sts$getTrash().getItem(0);
		if (!stack.isEmpty()) output.store(TRASH_KEY, ItemStack.CODEC, stack);
	}

	@Inject(
			method = "readAdditionalSaveData",
			at = @At("TAIL")
	)
	private void loadTrash(ValueInput input, CallbackInfo ci) {
		ItemStack stack = input.read(TRASH_KEY, ItemStack.CODEC).orElse(ItemStack.EMPTY);
		Inventory inventory = ((Player) (Object) this).getInventory();
		ItemStack legacy = inventory.getItem(Inventory.SLOT_BODY_ARMOR);
		if (stack.isEmpty() && !legacy.isEmpty()) {
			stack = legacy;
			inventory.setItem(Inventory.SLOT_BODY_ARMOR, ItemStack.EMPTY);
		}
		sts$getTrash().setItem(0, stack);
	}
}
