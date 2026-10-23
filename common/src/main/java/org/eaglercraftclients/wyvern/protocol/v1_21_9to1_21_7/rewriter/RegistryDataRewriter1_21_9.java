/*
 * This file is part of Wyvern, a fork of ViaBackwards (https://github.com/ViaVersion/ViaBackwards)
 * Copyright (C) 2016-2026 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.eaglercraftclients.wyvern.protocol.v1_21_9to1_21_7.rewriter;

import com.viaversion.nbt.tag.CompoundTag;
import org.eaglercraftclients.wyvern.api.BackwardsProtocol;
import org.eaglercraftclients.wyvern.api.rewriters.BackwardsRegistryRewriter;
import org.eaglercraftclients.wyvern.protocol.v1_21_9to1_21_7.storage.DimensionScaleStorage;
import org.eaglercraftclients.wyvern.protocol.v1_21_9to1_21_7.storage.ProtocolStorables1_21_9;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.RegistryEntry;

public final class RegistryDataRewriter1_21_9 extends BackwardsRegistryRewriter {

    private final BackwardsProtocol<?, ?, ?, ?> backwardsProtocol;

    public RegistryDataRewriter1_21_9(final BackwardsProtocol<?, ?, ?, ?> protocol) {
        super(protocol);
        this.backwardsProtocol = protocol;
    }

    @Override
    public void trackDimensionAndBiomes(final UserConnection connection, final String registryKey, final RegistryEntry[] entries) {
        super.trackDimensionAndBiomes(connection, registryKey, entries);
        if (!registryKey.equals("dimension_type")) {
            return;
        }

        final ProtocolStorables1_21_9 storables = connection.storables(backwardsProtocol);
        final DimensionScaleStorage dimensionScaleStorage = storables.dimensionScaleStorage();
        for (int i = 0; i < entries.length; i++) {
            final RegistryEntry entry = entries[i];
            final CompoundTag dimension = (CompoundTag) entry.tag();
            if (dimension == null) {
                continue;
            }

            final double coordinateScale = dimension.getDouble("coordinate_scale", 1);
            dimensionScaleStorage.setScale(i, coordinateScale);
        }
    }
}
