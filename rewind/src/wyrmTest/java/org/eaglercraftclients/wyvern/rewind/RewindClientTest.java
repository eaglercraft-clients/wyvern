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
package org.eaglercraftclients.wyvern.rewind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.client.ClientPipeline;
import com.viaversion.viaversion.client.ClientVia;
import com.viaversion.viaversion.client.EaglerLogin;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.embedded.EmbeddedChannel;
import java.util.UUID;
import org.eaglercraftclients.wyvern.client.WyvernClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * A 1.8 game (the Eaglercraft 1.8 client) joining newer servers through Wyvern and ViaRewind in Wyrm's client Via.
 * Real handshake and login bytes go through Via's handlers on Netty's embedded channel.
 */
class RewindClientTest {

    private static final int GAME = ProtocolVersion.v1_8.getVersion();
    private static final UUID PLAYER = UUID.fromString("11111111-2222-3333-4444-555555555555");

    @BeforeAll
    static void startVia() {
        ClientVia.setClient("Test", "1");
        ClientVia.start(new WyvernClient(), new RewindClient());
    }

    @Test
    void reachesNewerServers() {
        assertTrue(ClientVia.supports(GAME, ProtocolVersion.v1_9.getVersion()));
        assertTrue(ClientVia.supports(GAME, ProtocolVersion.v1_12_2.getVersion()));
        assertTrue(ClientVia.supports(GAME, ProtocolVersion.v1_21.getVersion()));
        assertTrue(ClientVia.supports(GAME, ProtocolVersion.v26_2.getVersion()));
    }

    @Test
    void a1_8GameJoinsA1_9Server() {
        assertLogin(ProtocolVersion.v1_9.getVersion());
    }

    @Test
    void a1_8GameJoinsA1_12_2Server() {
        assertLogin(ProtocolVersion.v1_12_2.getVersion());
    }

    @Test
    void a1_8GameJoinsA1_21Server() {
        assertLogin(ProtocolVersion.v1_21.getVersion());
    }

    @Test
    void a1_8GameJoinsA26_2Server() {
        assertLogin(ProtocolVersion.v26_2.getVersion());
    }

    private static void assertLogin(final int server) {
        final EmbeddedChannel channel = new EmbeddedChannel(new ChannelInboundHandlerAdapter());
        // Stand-ins for the game's framing handlers, which Via sits right after.
        channel.pipeline().addFirst("splitter", new ChannelInboundHandlerAdapter());
        channel.pipeline().addLast("prepender", new ChannelOutboundHandlerAdapter());
        ClientPipeline.install(channel, server, "splitter", "prepender");
        final EaglerLogin login = new EaglerLogin(server, "Rewind", PLAYER);

        final ByteBuf handshake = Unpooled.buffer();
        Types.VAR_INT.writePrimitive(handshake, 0x00);
        Types.VAR_INT.writePrimitive(handshake, GAME);
        Types.STRING.write(handshake, "localhost");
        Types.UNSIGNED_SHORT.write(handshake, 25565);
        Types.VAR_INT.writePrimitive(handshake, 2);
        channel.writeOutbound(handshake);
        final ByteBuf sent = (ByteBuf) channel.readOutbound();
        assertNotNull(sent, "the handshake must reach the server");
        assertEquals(0x00, Types.VAR_INT.readPrimitive(sent));
        assertEquals(server, Types.VAR_INT.readPrimitive(sent), "the server sees its own protocol");
        sent.readerIndex(0);
        assertTrue(login.swallow(bytes(sent)));

        // 1.8 Login Start: the name only.
        final ByteBuf start = Unpooled.buffer();
        Types.VAR_INT.writePrimitive(start, 0x00);
        Types.STRING.write(start, "Rewind");
        channel.writeOutbound(start);
        final ByteBuf loginStart = (ByteBuf) channel.readOutbound();
        assertNotNull(loginStart, "Login Start must reach the server");
        assertEquals(0x00, Types.VAR_INT.readPrimitive(loginStart));
        assertEquals("Rewind", Types.STRING.read(loginStart));
        loginStart.readerIndex(0);
        assertTrue(login.swallow(bytes(loginStart)));

        channel.writeInbound(Unpooled.wrappedBuffer(login.takeLoginSuccess()));
        final ByteBuf received = (ByteBuf) channel.readInbound();
        assertNotNull(received, "the game must get a Login Success");
        assertEquals(0x02, Types.VAR_INT.readPrimitive(received), "Login Success");
        assertEquals(PLAYER.toString(), Types.STRING.read(received), "1.8 writes the UUID as text");
        assertEquals("Rewind", Types.STRING.read(received));

        channel.finish();
    }

    private static byte[] bytes(final ByteBuf buf) {
        final byte[] out = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), out);
        return out;
    }
}
