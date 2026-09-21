package me.pajic.simple_trash_slot.platform;

//$ loader_util_import
import me.pajic.simple_trash_slot.platform.fabric.FabricLoaderUtil;

import java.nio.file.Path;

public interface MultiLoaderUtil {
    MultiLoaderUtil INSTANCE = /*$ loader_util_inst*/ new FabricLoaderUtil();

    boolean isModLoaded(String modId);
    boolean isDevEnv();
    Path configDir();
}
