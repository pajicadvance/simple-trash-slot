package me.pajic.simple_trash_slot.platform;

import java.nio.file.Path;

public interface Platform {

	boolean isDevelopmentEnvironment();

	default boolean isDebug() {
		return isDevelopmentEnvironment();
	}

	Path configDir();
}
