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

import io.korti.sleepwalk.Sleepwalk;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.stats.Stats;

public class PotionSleepwalk extends Effect {

    public PotionSleepwalk() {
        super(EffectType.BENEFICIAL, 0x23383f);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if(entityLivingBaseIn instanceof ServerPlayerEntity) {
            Sleepwalk.LOG.debug("Reduce time since rest.");
            ((ServerPlayerEntity) entityLivingBaseIn).addStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -20);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        // TODO: Needs balancing sometime.
        switch (amplifier) {
            case 0:
                return duration % 80 == 0;
            case 1:
                return duration % 60 == 0;
            case 2:
                return duration % 40 == 0;
            case 4:
                return duration % 20 == 0;
            default:
                return false;
        }
    }
}
