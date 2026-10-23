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
package org.eaglercraftclients.wyvern.protocol.v1_13to1_12_2.storage;

import com.viaversion.viaversion.connection.ProtocolStorablesBase;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class ProtocolStorables1_13 extends ProtocolStorablesBase {

    private final BackwardsBlockStorage backwardsBlockStorage = new BackwardsBlockStorage();
    private final TabCompleteStorage tabCompleteStorage = new TabCompleteStorage();
    private final NoteBlockStorage noteBlockStorage = new NoteBlockStorage();
    private PlayerPositionStorage1_13 playerPositionStorage;

    public BackwardsBlockStorage backwardsBlockStorage() {
        return backwardsBlockStorage;
    }

    public TabCompleteStorage tabCompleteStorage() {
        return tabCompleteStorage;
    }

    public NoteBlockStorage noteBlockStorage() {
        return noteBlockStorage;
    }

    public @Nullable PlayerPositionStorage1_13 playerPositionStorage() {
        return playerPositionStorage;
    }

    public void setPlayerPositionStorage(final PlayerPositionStorage1_13 playerPositionStorage) {
        this.playerPositionStorage = playerPositionStorage;
    }
}
