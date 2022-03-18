package me.alphamode.top_plus;

import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbePlugin;
import me.alphamode.top_plus.modules.mi.TopMI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class TopPlus implements ITheOneProbePlugin, ModInitializer {
    private final Map<String, Class<? extends ITheOneProbePlugin>> plugins = new HashMap<>();

    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        plugins.put("modern_industrialization", TopMI.class);

        plugins.forEach((modId, clazz) -> {
            if(FabricLoader.getInstance().isModLoaded(modId)) {
                try {
                    ITheOneProbePlugin plugin = clazz.getConstructor().newInstance();
                    plugin.onLoad(apiInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onInitialize() {}

    public static String MOD_ID = "top_plus";

    public static Identifier getResource(String path) {
        return new Identifier(MOD_ID, path);
    }
}
