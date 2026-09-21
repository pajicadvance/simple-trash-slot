package me.pajic.simple_trash_slot.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import me.pajic.simple_trash_slot.STS;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		STS.onInitialize();
        Registry.register(BuiltInRegistries.SOUND_EVENT, STS.id("trash"), STS.TRASH_SOUND);
	}
}
//?}
