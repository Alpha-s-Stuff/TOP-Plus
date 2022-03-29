package me.alphamode.top_plus;

import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbePlugin;
import me.alphamode.top_plus.modules.ae2.TopAE2;
import me.alphamode.top_plus.modules.mi.TopMI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TopPlus implements ITheOneProbePlugin, ModInitializer {
    private final Map<String, Class<? extends ITheOneProbePlugin>> plugins = new HashMap<>();
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        plugins.put("modern_industrialization", TopMI.class);
//        plugins.put("tconstruct", TopTc.class);
        plugins.put("ae2", TopAE2.class);
        LOGGER.info("Loading {} TOP Plus plugins", plugins.keySet().stream().filter(FabricLoader.getInstance()::isModLoaded).toList().size());
        plugins.forEach((modId, clazz) -> {
            if(FabricLoader.getInstance().isModLoaded(modId)) {
                try {
                    ITheOneProbePlugin plugin = clazz.getConstructor().newInstance();
                    plugin.onLoad(apiInstance);
                    LOGGER.info("Loaded TOP Plugin for mod: " + modId);
                } catch (Exception e) {
                    LOGGER.error("Failed to load TOP Plugin for mod " + modId);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onInitialize() {
    }

    public static String MOD_ID = "top_plus";

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
