package me.alphamode.top_plus.modules.ae2;

import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbePlugin;
import me.alphamode.top_plus.modules.ae2.config.AEConfigProvider;

public class TopAE2 implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe input) {
        input.registerProbeConfigProvider(new AEConfigProvider());
        input.registerProvider(new BlockEntityInfoProvider());
        input.registerProvider(new PartInfoProvider());
    }
}
