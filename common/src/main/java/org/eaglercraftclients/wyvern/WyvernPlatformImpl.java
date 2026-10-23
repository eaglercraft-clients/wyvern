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
import com.viaversion.viaversion.api.Via;
import java.io.File;
import java.util.logging.Logger;

public class WyvernPlatformImpl implements WyvernPlatform {

    private final Logger logger;

    public WyvernPlatformImpl() {
        logger = Via.getPlatform().createLogger("Wyvern");
        init(new File(getDataFolder(), "wyvern.yml"));
        enable();
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void disable() {
    }

    @Override
    public File getDataFolder() {
        return Via.getPlatform().getDataFolder();
    }

    @Override
    public boolean isOutdated() {
        return false;
    }
}
