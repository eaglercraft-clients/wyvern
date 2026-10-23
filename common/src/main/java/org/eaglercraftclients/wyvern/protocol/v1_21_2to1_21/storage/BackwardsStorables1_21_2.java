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
package org.eaglercraftclients.wyvern.protocol.v1_21_2to1_21.storage;

import com.viaversion.viaversion.connection.ProtocolStorablesBase;
import com.viaversion.viaversion.protocols.v1_21to1_21_2.storage.ClientVehicleStorage;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class BackwardsStorables1_21_2 extends ProtocolStorablesBase {

    private final InventoryStateIdStorage inventoryStateIdStorage = new InventoryStateIdStorage();
    private final ItemTagStorage itemTagStorage = new ItemTagStorage();
    private final PlayerStorage playerStorage = new PlayerStorage();
    private SignStorage signStorage = new SignStorage();
    private RecipeStorage recipeStorage;
    private ClientVehicleStorage clientVehicleStorage;

    public InventoryStateIdStorage inventoryStateIdStorage() {
        return inventoryStateIdStorage;
    }

    public ItemTagStorage itemTagStorage() {
        return itemTagStorage;
    }

    public PlayerStorage playerStorage() {
        return playerStorage;
    }

    public SignStorage signStorage() {
        return signStorage;
    }

    public void setSignStorage(final SignStorage signStorage) {
        this.signStorage = signStorage;
    }

    public RecipeStorage recipeStorage() {
        return recipeStorage;
    }

    public void setRecipeStorage(final RecipeStorage recipeStorage) {
        this.recipeStorage = recipeStorage;
    }

    public @Nullable ClientVehicleStorage clientVehicleStorage() {
        return clientVehicleStorage;
    }

    public void setClientVehicleStorage(final @Nullable ClientVehicleStorage clientVehicleStorage) {
        this.clientVehicleStorage = clientVehicleStorage;
    }
}
