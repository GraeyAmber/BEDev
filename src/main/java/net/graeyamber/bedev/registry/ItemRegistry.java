package net.graeyamber.bedev.registry;

import net.graeyamber.bedev.BEDev;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ItemRegistry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BEDev.MODID);


    public static final Supplier<BlockItem> GNEISS_BLOCK_ITEM        = ITEMS.registerSimpleBlockItem("gneiss_block"       , BlockRegistry.GNEISS_BLOCK       , new Item.Properties());
    public static final Supplier<BlockItem> GNEISS_BRICKS_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("gneiss_bricks_block", BlockRegistry.GNEISS_BRICKS_BLOCK, new Item.Properties());
    public static final Supplier<BlockItem> GNEISS_SMOOTH_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("gneiss_smooth_block", BlockRegistry.GNEISS_SMOOTH_BLOCK, new Item.Properties());
    public static final Supplier<BlockItem> GNEISS_TILES_BLOCK_ITEM  = ITEMS.registerSimpleBlockItem("gneiss_tiles_block" , BlockRegistry.GNEISS_TILES_BLOCK , new Item.Properties());

    public static final Supplier<BlockItem> MANUAL_BURNER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("manual_burner_block", BlockRegistry.MANUAL_BURNER_BLOCK, new Item.Properties());

    public static final Supplier<BlockItem> DIM_LAMP_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("dim_lamp_block", BlockRegistry.DIM_LAMP_BLOCK, new Item.Properties());
}
