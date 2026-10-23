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

import com.google.common.base.Preconditions;
import org.eaglercraftclients.wyvern.api.WyvernConfig;
import org.eaglercraftclients.wyvern.api.WyvernPlatform;

public final class Wyvern {

    private static WyvernPlatform platform;
    private static WyvernConfig config;

    public static void init(WyvernPlatform platform, WyvernConfig config) {
        Preconditions.checkArgument(Wyvern.platform == null, "Wyvern is already initialized");

        Wyvern.platform = platform;
        Wyvern.config = config;
    }

    public static WyvernPlatform getPlatform() {
        return platform;
    }

    public static WyvernConfig getConfig() {
        return config;
    }
}
