package me.alphamode.top_plus;

import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbePlugin;
import me.alphamode.top_plus.modules.mi.TopMI;
import me.alphamode.top_plus.modules.tinkers.TopTc;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TopPlus implements ITheOneProbePlugin, ModInitializer {
    private final Map<String, Class<? extends ITheOneProbePlugin>> plugins = new HashMap<>();

    @Override
    public void onLoad(ITheOneProbe apiInstance) {
//        plugins.put("modern_industrialization", TopMI.class);
//        plugins.put("tconstruct", TopTc.class);

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
    public void onInitialize() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            String modId = Registry.ITEM.getKey(stack.getItem()).getNamespace();
            lines.add(new TextComponent(ChatFormatting.BLUE + "" + ChatFormatting.ITALIC + FabricLoader.getInstance().getModContainer(modId)
                    .map(modContainer -> modContainer.getMetadata().getName())
                    .orElse(StringUtils.capitalize(modId))));
        });
    }

    public static String MOD_ID = "top_plus";

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
