package me.pajic.simple_trash_slot;

import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

public class STSUtil {

	public static int slotId = -1;

	public static void computeSlot(NonNullList<Slot> slots) {
		if (slotId == -1) {
			slotId = slots.getLast().index + 1;
			STS.debugLog("Trash slot ID: {}", slotId);
		}
	}

	@SuppressWarnings("resource")
	public static void playSound(Player player) {
		RandomSource random = player.level().getRandom();
		switch (STSConfig.CONFIG.sound()) {
			case LAVA_SIZZLE -> player.playSound(SoundEvents.LAVA_EXTINGUISH, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F);
			case PAPER_BIN_TOSS -> player.playSound(STS.TRASH_SOUND, 0.7F, 1 + (random.nextFloat() - random.nextFloat()) * 0.4F);
		}
	}
}
