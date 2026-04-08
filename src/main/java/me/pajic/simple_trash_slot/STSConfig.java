package me.pajic.simple_trash_slot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class STSConfig {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path FILE_PATH = STS.xplat().configDir().resolve("simple_trash_slot.json");
	public static Config CONFIG;

	public static void loadConfig() {
		readConfig();
		saveConfig();
	}

	private static void readConfig() {
		try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
			CONFIG = GSON.fromJson(reader, Config.class);
		} catch (FileNotFoundException | JsonSyntaxException e) {
			if (e.getCause() instanceof NumberFormatException) {
				STS.LOGGER.error("Stack size can be 2147483647 at most! Resetting config.", e);
			}
			STS.debugLog("Config doesn't exist or is malformed, initializing new mod config\n{}", e);
			initializeConfig();
		} catch (IOException e) {
			STS.debugLog("Failed to read mod config\n{}", e);
		}
	}

	private static void saveConfig() {
		try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
			GSON.toJson(CONFIG, writer);
		} catch (IOException e) {
			STS.debugLog("Failed to save mod config\n{}", e);
		}
	}

	private static void initializeConfig() {
		try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
			CONFIG = new Config();
			GSON.toJson(CONFIG, writer);
		} catch (IOException e) {
			STS.debugLog("Failed to initialize mod config\n{}", e);
		}
	}

	public static class Config {
		SlotPosition position;
		TrashSound sound;

		public Config() {
			position = SlotPosition.H2;
			sound = TrashSound.PAPER_BIN_TOSS;
		}

		public SlotPosition pos() {
			return position;
		}

		public TrashSound sound() {
			return sound;
		}
	}
}
