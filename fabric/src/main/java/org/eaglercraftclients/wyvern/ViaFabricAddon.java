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

package org.eaglercraftclients.wyvern;

import org.eaglercraftclients.wyvern.api.WyvernPlatform;
import org.eaglercraftclients.wyvern.fabric.util.LoggerWrapper;
import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

public class ViaFabricAddon implements WyvernPlatform, Runnable {
    private final Logger logger = new LoggerWrapper(LogManager.getLogger("Wyvern"));
    private File configDir;

    @Override
    public void run() {
        Path configDirPath = FabricLoader.getInstance().getConfigDir().resolve("Wyvern");
        configDir = configDirPath.toFile();
        this.init(new File(getDataFolder(), "config.yml"));
        this.enable();
    }

    @Override
    public void disable() {
        // Not possible
    }

    @Override
    public File getDataFolder() {
        return configDir;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
