package me.alphamode.top_plus.modules.tinkers;

import mcjty.theoneprobe.api.*;
import me.alphamode.top_plus.TopPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

public class TopTc implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe apiInstance) {
        apiInstance.registerProvider(new IProbeInfoProvider() {
            @Override
            public ResourceLocation getID() {
                return TopPlus.getResource("tc_plugin");
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
                if (world.getBlockEntity(data.getPos()) != null && world.getBlockEntity(data.getPos()) instanceof CastingBlockEntity castingBlock && castingBlock.getCoolingTime() > 0) {
                    probeInfo.progress(castingBlock.getTimer(), castingBlock.getCoolingTime());
                }
            }
        });
    }
}
