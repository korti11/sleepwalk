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
import io.korti.sleepwalk.common.potion.PotionSleepwalk;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public final class SleepwalkPotions {

    @ObjectHolder(Sleepwalk.MOD_ID + ":sleepwalk")
    public static PotionSleepwalk potionSleepwalk;

    @Mod.EventBusSubscriber(modid = Sleepwalk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {

        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Effect> event) {
            final IForgeRegistry<Effect> effectRegistry = event.getRegistry();

            effectRegistry.register(new PotionSleepwalk().setRegistryName(Sleepwalk.MOD_ID, "sleepwalk"));
        }

    }
}
