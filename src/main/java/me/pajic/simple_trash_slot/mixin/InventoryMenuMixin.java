package me.pajic.simple_trash_slot.mixin;

import me.pajic.simple_trash_slot.STS;
import me.pajic.simple_trash_slot.STSConfig;
import me.pajic.simple_trash_slot.STSUtil;
import me.pajic.simple_trash_slot.SlotPosition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InventoryMenu.class, priority = 1)
public abstract class InventoryMenuMixin extends AbstractCraftingMenu {

    public InventoryMenuMixin(MenuType<?> menuType, int containerId, int width, int height) {
        super(menuType, containerId, width, height);
    }

    @Inject(
			method = "<init>",
			at = @At("RETURN")
	)
    private void addTrashSlot(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
		STSUtil.computeSlot(slots);
		SlotPosition pos = STSConfig.CONFIG.pos();
        addSlot(new Slot(inventory, STSUtil.slotId - 5, pos.x + 1, pos.y + 1) {
            @Override
            public Identifier getNoItemIcon() {
                return STS.id("container/slot/trash");
            }

			@SuppressWarnings("DataFlowIssue")
			@Override
			public boolean isActive() {
				return !owner.hasContainerOpen() && owner.gameMode().isSurvival();
			}
        });
    }
}
