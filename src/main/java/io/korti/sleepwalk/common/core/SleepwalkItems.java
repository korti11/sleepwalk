/*
 *  Copyright 2020 Korti
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.korti.sleepwalk.common.core;

import io.korti.sleepwalk.Sleepwalk;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class SleepwalkItems {

    @ObjectHolder(Sleepwalk.MOD_ID + ":phantom_scale")
    public static Item phantomScale;

    @Mod.EventBusSubscriber(modid = Sleepwalk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> itemRegistry = event.getRegistry();

            itemRegistry.register(new Item(new Item.Properties().group(ItemGroup.BREWING))
                    .setRegistryName(Sleepwalk.MOD_ID, "phantom_scale"));
        }

    }

}
