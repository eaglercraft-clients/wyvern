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

import com.viaversion.viaversion.api.connection.StorableObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Per-connection memory of the exact channel names an Eaglercraft client has used.
 *
 * <p>The mapping in {@link EaglerChannels} drops upper-case letters. To send a channel back to the client under the
 * spelling it expects, the connection remembers every legacy name the client registered or sent on.
 */
public final class EaglerChannelStorage implements StorableObject {

    private final Map<String, String> modernToLegacy = new ConcurrentHashMap<>();

    /** Remembers that the client's {@code legacyChannel} travels as {@code modernChannel} on the server side. */
    public void learn(final String legacyChannel, final String modernChannel) {
        modernToLegacy.putIfAbsent(modernChannel, legacyChannel);
    }

    /** The spelling the client uses for {@code modernChannel}, or null if it has not used that channel yet. */
    public String legacyOf(final String modernChannel) {
        return modernToLegacy.get(modernChannel);
    }
}
