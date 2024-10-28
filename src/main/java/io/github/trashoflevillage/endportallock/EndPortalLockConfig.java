package io.github.trashoflevillage.endportallock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class EndPortalLockConfig {
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final String FILE_NAME = EndPortalLock.MOD_ID + ".json";
    public static final EndPortalLockConfig INSTANCE =
            new EndPortalLockConfig(FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME));

    protected final Path filePath;
    protected final File file;

    public EndPortalLockConfig(Path filePath) {
        this.filePath = filePath;
        file = filePath.toFile();
    }

    public Map<Integer, String> fromJson() {
        Type type = new TypeToken<Map<Integer, String>>(){}.getType();
        Map<Integer, String> config;
        try {
            config = GSON.fromJson(Files.readString(filePath), type);
        } catch (IOException e) {
            config = new HashMap<>();
        }
        return config;
    }

    public static Map<Integer, String> getDefaultConfig() {
        Map<Integer, String> config = new HashMap<>();

        for (int i = 1; i <= 12; i++)
            config.put(i, "minecraft:ender_eye");

        return config;
    }

    public void saveConfig(Map<Integer, String> config) {
        try {
            FileWriter writer = new FileWriter(file);
            Type typeObject = new TypeToken<HashMap>() {}.getType();
            String json = GSON.toJson(config, typeObject);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeConfig() {
        if (file.exists()) return;
        saveConfig(getDefaultConfig());
    }
}
