package me.alphamode.top_plus;

import me.alphamode.top_plus.api.ItemModTooltipCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TopPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if(!isModNameAlreadyPresent(lines, Registry.ITEM.getKey(stack.getItem()).getNamespace()))
                lines.add(ItemModTooltipCallback.EVENT.invoker().getTooltip(stack, context, lines));
        });
    }

    public static boolean isModNameAlreadyPresent(List<Component> tooltip, String modName) {
        if (tooltip.size() > 1) {
            Component line = tooltip.get(tooltip.size() - 1);
            String lineString = line.getString();
            String withoutFormatting = ChatFormatting.stripFormatting(lineString);
            return modName.equals(withoutFormatting);
        }
        return false;
    }
}
