package me.alphamode.top_plus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import org.apache.commons.lang3.StringUtils;

public class TopPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            String modId = Registry.ITEM.getKey(stack.getItem()).getNamespace();
            lines.add(new TextComponent(ChatFormatting.BLUE + "" + ChatFormatting.ITALIC + FabricLoader.getInstance().getModContainer(modId)
                    .map(modContainer -> modContainer.getMetadata().getName())
                    .orElse(StringUtils.capitalize(modId))));
        });
    }
}
