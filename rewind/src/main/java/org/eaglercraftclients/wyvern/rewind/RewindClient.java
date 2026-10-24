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

import com.viaversion.viarewind.ViaRewindConfig;
import com.viaversion.viarewind.api.ViaRewindPlatform;
import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

/**
 * ViaRewind inside an Eaglercraft client that embeds Via (Wyrm's {@code ClientVia}), next to {@link
 * org.eaglercraftclients.wyvern.client.WyvernClient}. Wyvern takes a game up from 1.9; this takes a 1.8 game (the
 * Eaglercraft 1.8 client) up to 1.9, so together they reach every newer server.
 *
 * <p>Pass it after Wyvern, which it needs: {@code ClientVia.start(new WyvernClient(), new RewindClient())}. Settings are
 * the defaults and live in memory.
 */
public final class RewindClient implements ViaRewindPlatform, Runnable {

    private static final Logger LOGGER = Logger.getLogger("ViaRewind");

    @Override
    public void run() {
        init(new InMemoryConfig());
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /** Not used for anything: the config is in memory. */
    @Override
    public File getDataFolder() {
        return new File("viarewind");
    }

    private static final class InMemoryConfig extends ViaRewindConfig {

        // A file name without a directory, so that reloading never tries to create one.
        InMemoryConfig() {
            super(new File("viarewind.yml"), LOGGER);
        }

        @Override
        public void save(final File location, final Map<String, Object> config) {
        }
    }
}
