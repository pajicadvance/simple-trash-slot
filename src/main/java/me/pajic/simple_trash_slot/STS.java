package me.pajic.simple_trash_slot;

import me.pajic.simple_trash_slot.platform.MultiLoaderUtil;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class STS {

    public static final String MOD_ID = /*$ mod_id*/ "simple_trash_slot";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final SoundEvent TRASH_SOUND = SoundEvent.createVariableRangeEvent(id("trash"));

    public static void onInitialize() {}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void debugLog(String message, Object ... args) {
        if (MultiLoaderUtil.INSTANCE.isDevEnv()) LOGGER.info(message, args);
    }
}
