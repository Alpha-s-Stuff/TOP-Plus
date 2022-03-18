package me.alphamode.top_plus.modules.mi;

import aztech.modern_industrialization.compat.megane.holder.EnergyComponentHolder;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.config.Config;
import me.alphamode.top_plus.TopPlus;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static mcjty.theoneprobe.api.TextStyleClass.PROGRESS;

public class TopMI implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        apiInstance.registerProvider(new IProbeInfoProvider() {
            @Override
            public Identifier getID() {
                return TopPlus.getResource("mi_plugin");
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData data) {
                if(world.getBlockEntity(data.getPos()) instanceof EnergyComponentHolder energyComponentHolder)
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
            probeInfo.text(CompoundText.create().style(PROGRESS).text("E: " + ElementProgress.format(energy, Config.rfFormat.get(), new LiteralText("EU"))));
        }
    }
}
