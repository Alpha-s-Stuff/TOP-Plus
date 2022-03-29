package me.alphamode.top_plus.modules.mi;

import aztech.modern_industrialization.compat.megane.holder.EnergyComponentHolder;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.config.Config;
import me.alphamode.top_plus.TopPlus;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static mcjty.theoneprobe.api.TextStyleClass.PROGRESS;

public class TopMI implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        apiInstance.registerProvider(new IProbeInfoProvider() {
            @Override
            public ResourceLocation getID() {
                return TopPlus.getResource("mi_plugin");
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
                if(world.getBlockEntity(data.getPos()) != null && world.getBlockEntity(data.getPos()) instanceof EnergyComponentHolder energyComponentHolder)
                    addEnergyInfo(probeInfo, energyComponentHolder.getEnergyComponent().getEu(), energyComponentHolder.getEnergyComponent().getCapacity());
            }
        });
    }

    private void addEnergyInfo(IProbeInfo probeInfo, long energy, long maxEnergy) {
        if (Config.getDefaultConfig().getRFMode() == 1) {
            probeInfo.progress(energy, maxEnergy,
                    probeInfo.defaultProgressStyle()
                            .suffix("EU")
                            .filledColor(Config.rfbarFilledColor)
                            .alternateFilledColor(Config.rfbarAlternateFilledColor)
                            .borderColor(Config.rfbarBorderColor)
                            .numberFormat(Config.rfFormat.get()));
        } else {
            probeInfo.text(CompoundText.create().style(PROGRESS).text("E: " + ElementProgress.format(energy, Config.rfFormat.get(), new TextComponent("EU"))));
        }
    }
}
