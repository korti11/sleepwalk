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

package io.korti.sleepwalk.common.loot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.korti.sleepwalk.Sleepwalk;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.LootFunctionManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

public class ExtraLoot extends LootModifier {

    private final Item drop;
    private final int rolls;
    private final ILootFunction[] lootFunctions;
    private final BiFunction<ItemStack, LootContext, ItemStack> combinedLootFunctions;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected ExtraLoot(ILootCondition[] conditionsIn, Item drop, int rolls, ILootFunction[] lootFunctions) {
        super(conditionsIn);
        this.drop = drop;
        this.rolls = rolls;
        this.lootFunctions = lootFunctions;
        this.combinedLootFunctions = LootFunctionManager.combine(lootFunctions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        for (int i = 0; i < this.rolls; i++) {
            ItemStack dropStack = new ItemStack(drop);
            dropStack = combinedLootFunctions.apply(dropStack, context);
            generatedLoot.add(dropStack);
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ExtraLoot> {

        private static final Gson GSON_INSTANCE = (new GsonBuilder())
                .registerTypeHierarchyAdapter(ILootFunction.class, LootFunctionManager.func_237450_a_()).create();


        @Override
        public ExtraLoot read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            final Item drop = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(object, "drop")));
            final int rolls = JSONUtils.getInt(object, "rolls");
            final ILootFunction[] lootFunctions = GSON_INSTANCE
                    .fromJson(JSONUtils.getJsonArray(object, "functions"), ILootFunction[].class);
            Sleepwalk.LOG.debug("Modifier read.");
            return new ExtraLoot(ailootcondition, drop, rolls, lootFunctions);
        }
    }

}
