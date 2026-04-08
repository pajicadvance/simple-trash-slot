package me.pajic.simple_trash_slot.platform.neoforge;

//? neoforge {

/*import me.pajic.simple_trash_slot.STS;
import net.minecraft.core.registries.Registries;import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(STS.MOD_ID)
@EventBusSubscriber(modid = STS.MOD_ID)
public class NeoforgeEntrypoint {

	@SubscribeEvent
	private static void onCommonSetup(FMLCommonSetupEvent event) {
		STS.onInitialize();
	}

	@SubscribeEvent
	private static void registerSound(RegisterEvent event) {
		event.register(Registries.SOUND_EVENT, registry -> registry.register(STS.id("trash"), STS.TRASH_SOUND));
	}
}
*///?}
