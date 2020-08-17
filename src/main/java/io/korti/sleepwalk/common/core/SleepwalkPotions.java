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
import io.korti.sleepwalk.common.potion.InstantEffectSleep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public final class SleepwalkPotions {

    @ObjectHolder(Sleepwalk.MOD_ID + ":sleepwalk")
    public static EffectSleepwalk effectSleepwalk;

    @ObjectHolder(Sleepwalk.MOD_ID + ":sleep")
    public static InstantEffectSleep instantEffectSleep;

    @Mod.EventBusSubscriber(modid = Sleepwalk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {

        @SubscribeEvent
        public static void registerEffects(final RegistryEvent.Register<Effect> event) {
            final IForgeRegistry<Effect> effectRegistry = event.getRegistry();

            effectRegistry.register(new EffectSleepwalk().setRegistryName(Sleepwalk.MOD_ID, "sleepwalk"));
            effectRegistry.register(new InstantEffectSleep().setRegistryName(Sleepwalk.MOD_ID, "sleep"));
        }

        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Potion> event) {
            final IForgeRegistry<Potion> potionRegistry = event.getRegistry();

            final int tickSecondOffset = 20 * 60;

            final Potion potionSleepwalk;
            final Potion potionStrongSleepwalk;
            final Potion potionStrongestSleepwalk;
            final Potion potionStopSleepwalk;

            potionRegistry.register(potionSleepwalk = new Potion("sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 0, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "sleepwalk"));
            potionRegistry.register(potionStrongSleepwalk = new Potion("strong_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 1, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "strong_sleepwalk"));
            potionRegistry.register(potionStrongestSleepwalk = new Potion("strongest_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 2, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "strongest_sleepwalk"));
            potionRegistry.register(potionStopSleepwalk = new Potion("stop_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 5, 3, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "stop_sleepwalk"));

            final Potion potionLongerSleepwalk;
            final Potion potionLongerStrongSleepwalk;
            final Potion potionLongerStrongestSleepwalk;
            final Potion potionLongerStopSleepwalk;

            potionRegistry.register(potionLongerSleepwalk = new Potion("longer_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 0, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_sleepwalk"));
            potionRegistry.register(potionLongerStrongSleepwalk = new Potion("longer_strong_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 1, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_strong_sleepwalk"));
            potionRegistry.register(potionLongerStrongestSleepwalk = new Potion("longer_strongest_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 2, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_strongest_sleepwalk"));
            potionRegistry.register(potionLongerStopSleepwalk = new Potion("longer_stop_sleepwalk",
                    new EffectInstance(effectSleepwalk, tickSecondOffset * 10, 3, false, false)
            ).setRegistryName(Sleepwalk.MOD_ID, "longer_stop_sleepwalk"));

            final Potion potionSleep;
            final Potion potionStrongSleep;
            final Potion potionStrongestSleep;

            potionRegistry.register(potionSleep = new Potion("sleep",
                    new EffectInstance(instantEffectSleep, 1)
            ).setRegistryName(Sleepwalk.MOD_ID, "sleep"));
            potionRegistry.register(potionStrongSleep = new Potion("strong_sleep",
                    new EffectInstance(instantEffectSleep, 1, 1)
            ).setRegistryName(Sleepwalk.MOD_ID, "strong_sleep"));
            potionRegistry.register(potionStrongestSleep = new Potion("strongest_sleep",
                    new EffectInstance(instantEffectSleep, 1, 2)
            ).setRegistryName(Sleepwalk.MOD_ID, "strongest_sleep"));

            // TODO: Fix recipe conflict.
            addBrewingRecipe(Items.PHANTOM_MEMBRANE, Potions.AWKWARD, potionSleepwalk);
            addBrewingRecipe(Items.GLOWSTONE_DUST, potionSleepwalk, potionStrongSleepwalk);
            addBrewingRecipe(Items.GLOWSTONE_DUST, potionStrongSleepwalk, potionStrongestSleepwalk);
            addBrewingRecipe(Items.PHANTOM_MEMBRANE, potionStrongestSleepwalk, potionStopSleepwalk);

            addBrewingRecipe(Items.REDSTONE, potionSleepwalk, potionLongerSleepwalk);
            addBrewingRecipe(Items.REDSTONE, potionStrongSleepwalk, potionLongerStrongSleepwalk);
            addBrewingRecipe(Items.REDSTONE, potionStrongestSleepwalk, potionLongerStrongestSleepwalk);
            addBrewingRecipe(Items.REDSTONE, potionStopSleepwalk, potionLongerStopSleepwalk);

            addBrewingRecipe(Items.GLOWSTONE_DUST, potionLongerSleepwalk, potionLongerStrongSleepwalk);
            addBrewingRecipe(Items.GLOWSTONE_DUST, potionLongerStrongSleepwalk, potionLongerStrongestSleepwalk);
            addBrewingRecipe(Items.PHANTOM_MEMBRANE, potionLongerStrongestSleepwalk, potionLongerStopSleepwalk);
        }

        private static void addBrewingRecipe(Item itemInput, Potion potionInput, Potion potionOutput) {
            final Ingredient input = Ingredient.fromItems(itemInput);
            final ItemStack potionInputStack = new ItemStack(Items.POTION);
            final Ingredient potionBase = Ingredient
                    .fromStacks(PotionUtils.addPotionToItemStack(potionInputStack, potionInput));

            BrewingRecipeRegistry.addRecipe(
                    potionBase, input, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potionOutput)
            );
        }
    }
}
