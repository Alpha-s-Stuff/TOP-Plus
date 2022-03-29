package me.alphamode.top_plus.api;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface ItemModTooltipCallback {
    Event<ItemModTooltipCallback> EVENT = EventFactory.createArrayBacked(ItemModTooltipCallback.class, callbacks -> (stack, context, lines) -> {
        for (ItemModTooltipCallback callback : callbacks) {
            Component info = callback.getTooltip(stack, context, lines);
            if(info != null)
                return info;
        }
        String modId = Registry.ITEM.getKey(stack.getItem()).getNamespace();
        return new TextComponent(ChatFormatting.BLUE + "" + ChatFormatting.ITALIC + FabricLoader.getInstance().getModContainer(modId)
                .map(modContainer -> modContainer.getMetadata().getName())
                .orElse(StringUtils.capitalize(modId)));
    });

    Component getTooltip(ItemStack stack, TooltipFlag context, List<Component> lines);
}
