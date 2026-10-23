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
package org.eaglercraftclients.wyvern.protocol.v1_16_4to1_16_3;

import org.eaglercraftclients.wyvern.api.BackwardsProtocol;
import org.eaglercraftclients.wyvern.protocol.v1_16_4to1_16_3.storage.PlayerHandStorage;
import org.eaglercraftclients.wyvern.protocol.v1_16_4to1_16_3.storage.ProtocolStorables1_16_4;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.remapper.PacketHandlers;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.connection.ProtocolStorablesBase;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ClientboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ServerboundPackets1_16_2;

public class Protocol1_16_4To1_16_3 extends BackwardsProtocol<ClientboundPackets1_16_2, ClientboundPackets1_16_2, ServerboundPackets1_16_2, ServerboundPackets1_16_2> {

    public Protocol1_16_4To1_16_3() {
        super(ClientboundPackets1_16_2.class, ClientboundPackets1_16_2.class, ServerboundPackets1_16_2.class, ServerboundPackets1_16_2.class);
    }

    @Override
    protected void registerPackets() {
        registerServerbound(ServerboundPackets1_16_2.EDIT_BOOK, new PacketHandlers() {
            @Override
            public void register() {
                map(Types.ITEM1_13_2);
                map(Types.BOOLEAN);
                handler(wrapper -> {
                    int slot = wrapper.read(Types.VAR_INT);
                    if (slot == 1) {
                        wrapper.write(Types.VAR_INT, 40); // offhand
                    } else {
                        ProtocolStorables1_16_4 storables = wrapper.user().storables(Protocol1_16_4To1_16_3.this);
                        wrapper.write(Types.VAR_INT, storables.playerHandStorage().getCurrentHand());
                    }
                });
            }
        });

        registerServerbound(ServerboundPackets1_16_2.SET_CARRIED_ITEM, wrapper -> {
            short slot = wrapper.passthrough(Types.SHORT);
            ProtocolStorables1_16_4 storables = wrapper.user().storables(this);
            storables.playerHandStorage().setCurrentHand(slot);
        });
    }

    @Override
    public void init(UserConnection user) {
    }

    @Override
    public ProtocolStorablesBase createStorables() {
        return new ProtocolStorables1_16_4();
    }
}
