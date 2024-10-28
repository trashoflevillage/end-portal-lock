package io.github.trashoflevillage.endportallock.blocks;

import io.github.trashoflevillage.endportallock.EndPortalLock;
import io.github.trashoflevillage.endportallock.blocks.custom.CustomEndPortalFrameBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CUSTOM_PORTAL_FRAME1 =
            registerBlock("custom_portal_frame_1",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            1));
    public static final Block CUSTOM_PORTAL_FRAME2 =
            registerBlock("custom_portal_frame_2",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            2));
    public static final Block CUSTOM_PORTAL_FRAME3 =
            registerBlock("custom_portal_frame_3",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            3));
    public static final Block CUSTOM_PORTAL_FRAME4 =
            registerBlock("custom_portal_frame_4",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            4));
    public static final Block CUSTOM_PORTAL_FRAME5 =
            registerBlock("custom_portal_frame_5",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            5));
    public static final Block CUSTOM_PORTAL_FRAME6 =
            registerBlock("custom_portal_frame_6",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            6));
    public static final Block CUSTOM_PORTAL_FRAME7 =
            registerBlock("custom_portal_frame_7",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            7));
    public static final Block CUSTOM_PORTAL_FRAME8 =
            registerBlock("custom_portal_frame_8",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            8));
    public static final Block CUSTOM_PORTAL_FRAME9 =
            registerBlock("custom_portal_frame_9",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            9));
    public static final Block CUSTOM_PORTAL_FRAME10 =
            registerBlock("custom_portal_frame_10",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            10));
    public static final Block CUSTOM_PORTAL_FRAME11 =
            registerBlock("custom_portal_frame_11",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            11));
    public static final Block CUSTOM_PORTAL_FRAME12 =
            registerBlock("custom_portal_frame_12",
                    new CustomEndPortalFrameBlock(AbstractBlock.Settings.copy(Blocks.END_PORTAL_FRAME),
                            12));

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, true);
    }

    private static Block registerBlock(String name, Block block, boolean hasBlockItem) {
        if (hasBlockItem) registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,
                Identifier.of(EndPortalLock.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(EndPortalLock.MOD_ID, name),
                new BlockItem(block,
                        new Item.Settings()));
    }

    private static Block registerBlock(String name, Block block, Item.Settings itemSettings) {
        registerBlockItem(name, block, itemSettings);
        return Registry.register(Registries.BLOCK,
                Identifier.of(EndPortalLock.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block, Item.Settings settings) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(EndPortalLock.MOD_ID, name),
                new BlockItem(block,
                        settings));
    }

    public static void registerModBlocks() {
        EndPortalLock.LOGGER.info("Registering blocks for " + EndPortalLock.MOD_ID);
    }
}
