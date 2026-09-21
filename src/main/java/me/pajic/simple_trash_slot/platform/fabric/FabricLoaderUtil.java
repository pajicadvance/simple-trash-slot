package me.pajic.simple_trash_slot.platform.fabric;

//? fabric {

import me.pajic.simple_trash_slot.platform.MultiLoaderUtil;
import net.fabricmc.loader.api.FabricLoader;import java.nio.file.Path;

public class FabricLoaderUtil implements MultiLoaderUtil {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnv() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
//?}
