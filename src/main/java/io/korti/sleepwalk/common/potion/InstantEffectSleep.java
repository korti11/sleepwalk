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

package io.korti.sleepwalk.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;

public class InstantEffectSleep extends InstantEffect {

    public InstantEffectSleep() {
        super(EffectType.BENEFICIAL, 0x2c464f);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if(entityLivingBaseIn instanceof ServerPlayerEntity) {
            final ServerPlayerEntity playerEntity = (ServerPlayerEntity) entityLivingBaseIn;
            final StatisticsManager stats = playerEntity.getStats();
            final int timeSinceRest = stats.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
            final Stat<ResourceLocation> stat = Stats.CUSTOM.get(Stats.TIME_SINCE_REST);
            switch (amplifier) {
                case 0:
                    stats.setValue(playerEntity, stat, timeSinceRest - (timeSinceRest / 4));
                    break;
                case 1:
                    stats.setValue(playerEntity, stat, timeSinceRest / 2);
                    break;
                case 2:
                    stats.setValue(playerEntity, stat, 0);
                    break;
            }
        }
    }
}
