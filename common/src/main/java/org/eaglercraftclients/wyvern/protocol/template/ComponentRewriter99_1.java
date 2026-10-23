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
package org.eaglercraftclients.wyvern.protocol.template;

import com.viaversion.nbt.tag.CompoundTag;
import org.eaglercraftclients.wyvern.api.BackwardsProtocol;
import org.eaglercraftclients.wyvern.api.rewriters.text.NBTComponentRewriter;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.protocols.v1_21_11to26_1.packet.ClientboundPacket26_1;

final class ComponentRewriter99_1 extends NBTComponentRewriter<ClientboundPacket26_1> {

    public ComponentRewriter99_1(final BackwardsProtocol<ClientboundPacket26_1, ?, ?, ?> protocol) {
        super(protocol);
    }

    @Override
    protected void handleShowItem(final UserConnection connection, final CompoundTag itemTag, final CompoundTag componentsTag) {
        super.handleShowItem(connection, itemTag, componentsTag);
        if (componentsTag == null) {
            return;
        }

        // Remove or update data from componentsTag
        // Newly added data which is not handled otherwise needs to be removed to prevent errors on the client
    }
}
