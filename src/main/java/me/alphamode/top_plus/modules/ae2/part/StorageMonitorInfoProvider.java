/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2015, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package me.alphamode.top_plus.modules.ae2.part;

import appeng.api.implementations.parts.IStorageMonitorPart;
import appeng.api.parts.IPart;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import me.alphamode.top_plus.modules.ae2.TheOneProbeText;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class StorageMonitorInfoProvider implements IPartProbInfoProvider {

    @Override
    public void addProbeInfo(IPart part, ProbeMode mode, IProbeInfo probeInfo, Player player, Level level,
            BlockState blockState, IProbeHitData data) {
        if (part instanceof IStorageMonitorPart monitor) {

            var displayed = monitor.getDisplayed();
            var isLocked = monitor.isLocked();

            if (displayed != null) {
                probeInfo.text(displayed.getDisplayName());
            }

            probeInfo.text(isLocked ? TheOneProbeText.LOCKED.getTranslationComponent()
                    : TheOneProbeText.UNLOCKED.getTranslationComponent());
        }
    }

}
