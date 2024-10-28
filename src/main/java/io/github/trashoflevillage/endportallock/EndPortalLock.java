package io.github.trashoflevillage.endportallock;

import io.github.trashoflevillage.endportallock.blocks.ModBlocks;
import io.github.trashoflevillage.endportallock.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EndPortalLock implements ModInitializer {
	public static final String MOD_ID = "endportallock";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Map<Integer, String> PORTAL_ITEMS;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		EndPortalLockConfig.INSTANCE.initializeConfig();
		PORTAL_ITEMS = EndPortalLockConfig.INSTANCE.fromJson();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}