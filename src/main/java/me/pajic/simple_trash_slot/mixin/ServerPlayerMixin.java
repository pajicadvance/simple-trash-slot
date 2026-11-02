package me.pajic.simple_trash_slot.mixin;

import me.pajic.simple_trash_slot.STSUtil;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

	@Inject(
			method = "restoreFrom",
			at = @At("TAIL")
	)
	private void restoreTrash(ServerPlayer oldPlayer, boolean restoreAll, CallbackInfo ci) {
		STSUtil.getTrash((ServerPlayer) (Object) this).setItem(0, STSUtil.getTrash(oldPlayer).removeItemNoUpdate(0));
	}
}
