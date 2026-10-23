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

package org.eaglercraftclients.wyvern.eagler;

import com.viaversion.viaversion.api.connection.UserConnection;
import org.eaglercraftclients.wyvern.Wyvern;

/**
 * Carries Eaglercraft's plugin channels across the 1.12.2 / 1.13 boundary, where channel names stop being free text.
 * Used by the 1.13 to 1.12.2 protocol for plugin messages and for the {@code REGISTER} / {@code UNREGISTER} lists.
 */
public final class EaglerChannelBridge {

    private EaglerChannelBridge() {
    }

    /**
     * Client to server: the 1.13+ name for an Eaglercraft client's channel, or null when the channel is not an Eaglercraft
     * one (or the bridge is switched off) and ViaVersion's own mapping should decide.
     */
    public static String toModern(final UserConnection user, final String legacyChannel) {
        if (!Wyvern.getConfig().isEaglerChannelBridge()
            || !EaglerChannels.isLegacyEaglerChannel(legacyChannel, Wyvern.getConfig().eaglerExtraChannels())) {
            return null;
        }
        final String modern = EaglerChannels.toModern(legacyChannel);
        EaglerChannelStorage storage = user.get(EaglerChannelStorage.class);
        if (storage == null) {
            storage = new EaglerChannelStorage();
            user.put(storage);
        }
        storage.learn(legacyChannel, modern);
        return modern;
    }

    /**
     * Server to client: the spelling an Eaglercraft client expects for a 1.13+ channel, or null when the channel is not one
     * the client has used (then ViaVersion's own mapping decides).
     */
    public static String toLegacy(final UserConnection user, final String modernChannel) {
        if (!Wyvern.getConfig().isEaglerChannelBridge() || !EaglerChannels.isModernEaglerChannel(modernChannel)) {
            return null;
        }
        final EaglerChannelStorage storage = user.get(EaglerChannelStorage.class);
        return storage != null ? storage.legacyOf(modernChannel) : null;
    }
}
