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
import io.korti.sleepwalk.common.potion.EffectSleepwalk;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public final class SleepwalkPotions {

    @ObjectHolder(Sleepwalk.MOD_ID + ":sleepwalk")
    public static EffectSleepwalk effectSleepwalk;

    @Mod.EventBusSubscriber(modid = Sleepwalk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {

        @SubscribeEvent
        public static void registerEffects(final RegistryEvent.Register<Effect> event) {
            final IForgeRegistry<Effect> effectRegistry = event.getRegistry();

            effectRegistry.register(new EffectSleepwalk().setRegistryName(Sleepwalk.MOD_ID, "sleepwalk"));
        }

        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Potion> event) {
            final IForgeRegistry<Potion> potionRegistry = event.getRegistry();

            final int tickSecondOffset = 20 * 60;
            potionRegistry.register(new Potion("sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 0, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "sleepwalk"));
            potionRegistry.register(new Potion("strong_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 1, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "strong_sleepwalk"));
            potionRegistry.register(new Potion("strongest_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 2, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "strongest_sleepwalk"));
            potionRegistry.register(new Potion("stop_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 3, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "stop_sleepwalk"));

            potionRegistry.register(new Potion("longer_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 0, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_sleepwalk"));
            potionRegistry.register(new Potion("longer_strong_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 1, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_strong_sleepwalk"));
            potionRegistry.register(new Potion("longer_strongest_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 2, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_strongest_sleepwalk"));
            potionRegistry.register(new Potion("longer_stop_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 3, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_stop_sleepwalk"));
        }
    }
}
