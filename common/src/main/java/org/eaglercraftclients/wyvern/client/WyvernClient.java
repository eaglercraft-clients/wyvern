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
package org.eaglercraftclients.wyvern.client;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.eaglercraftclients.wyvern.WyvernConfig;
import org.eaglercraftclients.wyvern.api.WyvernPlatform;

/**
 * Wyvern inside an Eaglercraft client that embeds Via (Wyrm's {@code ClientVia}). It lets a client join servers
 * <em>newer</em> than the game: the game is the "player", Wyvern registers the downgrading protocols, and Via finds
 * the path from the game's protocol up to the server's.
 *
 * <p>Pass it to {@code ClientVia.start}, which runs it once Via is up and before Via works out its protocol paths:
 * <pre>{@code ClientVia.start(new WyvernClient());}</pre>
 * Settings are the defaults and live in memory; nothing is read from or written to disk.
 */
public final class WyvernClient implements WyvernPlatform, Runnable {

    private static final Logger LOGGER = Logger.getLogger("Wyvern");

    @Override
    public void run() {
        init(new InMemoryConfig());
        enable();
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void disable() {
    }

    /** Not used for anything: the config is in memory. */
    @Override
    public File getDataFolder() {
        return new File("wyvern");
    }

    private static final class InMemoryConfig extends WyvernConfig {

        // A file name without a directory, so that reloading never tries to create one.
        InMemoryConfig() {
            super(new File("wyvern.yml"), LOGGER);
        }

        // No file and no YAML: the defaults are what the getters fall back to, except for the one section they read
        // without a fallback. (Reading config.yml from the jar needs a URL stream, which a browser build lacks.)
        @Override
        public Map<String, Object> loadConfig(final File location) {
            final Map<String, Object> values = new HashMap<>();
            values.put("dialog-style", new HashMap<String, Object>());
            return values;
        }

        @Override
        public void save(final File location, final Map<String, Object> config) {
        }
    }
}
