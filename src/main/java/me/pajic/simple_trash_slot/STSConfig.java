package me.pajic.simple_trash_slot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import me.pajic.simple_trash_slot.platform.MultiLoaderUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class STSConfig {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path FILE_PATH = MultiLoaderUtil.INSTANCE.configDir().resolve("simple_trash_slot.json");
	public static Config CONFIG = new Config();

	public static void loadConfig() {
		readConfig();
		saveConfig();
	}

	private static void readConfig() {
		CONFIG = null;
		try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
			CONFIG = GSON.fromJson(reader, Config.class);
		} catch (FileNotFoundException e) {
			STS.debugLog("Config doesn't exist, initializing new mod config");
		} catch (JsonParseException e) {
			STS.LOGGER.error("Config is malformed, resetting to defaults", e);
		} catch (IOException e) {
			STS.LOGGER.error("Failed to read mod config, using defaults", e);
		}
		if (CONFIG == null) CONFIG = new Config();
		CONFIG.validate();
	}

	private static void saveConfig() {
		try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
			GSON.toJson(CONFIG, writer);
		} catch (IOException e) {
			STS.LOGGER.error("Failed to save mod config", e);
		}
	}

	public static class Config {
		SlotPosition position;
		TrashSound sound;

		public Config() {
			position = SlotPosition.H2;
			sound = TrashSound.PAPER_BIN_TOSS;
		}

		private void validate() {
			Config defaults = new Config();
			if (position == null) {
				STS.LOGGER.warn("Invalid trash slot position in config, using {}", defaults.position);
				position = defaults.position;
			}
			if (sound == null) {
				STS.LOGGER.warn("Invalid trash sound in config, using {}", defaults.sound);
				sound = defaults.sound;
			}
		}

		public SlotPosition pos() {
			return position;
		}

		public TrashSound sound() {
			return sound;
		}
	}
}
