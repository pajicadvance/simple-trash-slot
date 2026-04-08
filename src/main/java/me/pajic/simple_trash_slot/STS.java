package me.pajic.simple_trash_slot;

import me.pajic.simple_trash_slot.platform.Platform;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import me.pajic.simple_trash_slot.platform.fabric.FabricPlatform;
//?} neoforge {
/*import me.pajic.simple_trash_slot.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class STS {

	public static final String MOD_ID = /*$ mod_id*/ "simple_trash_slot";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Platform PLATFORM = createPlatformInstance();
	public static final SoundEvent TRASH_SOUND = SoundEvent.createVariableRangeEvent(id("trash"));

	public static void onInitialize() {}

	public static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static void debugLog(String message, Object ... args) {
		if (PLATFORM.isDebug()) LOGGER.info(message, args);
	}
}
