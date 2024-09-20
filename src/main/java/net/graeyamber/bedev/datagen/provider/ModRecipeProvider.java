package net.graeyamber.bedev.datagen.provider;

import net.graeyamber.bedev.registry.BlockRegistry;
import net.graeyamber.bedev.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.GNEISS_SMOOTH_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', ItemRegistry.GNEISS_BLOCK_ITEM.get())
                .unlockedBy("has_gneiss", has(ItemRegistry.GNEISS_BLOCK_ITEM.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.GNEISS_TILES_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', ItemRegistry.GNEISS_SMOOTH_BLOCK_ITEM.get())
                .unlockedBy("has_gneiss", has(ItemRegistry.GNEISS_BLOCK_ITEM.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.GNEISS_BRICKS_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', ItemRegistry.GNEISS_TILES_BLOCK_ITEM.get())
                .unlockedBy("has_gneiss", has(ItemRegistry.GNEISS_BLOCK_ITEM.get())).save(recipeOutput);
    }
}
