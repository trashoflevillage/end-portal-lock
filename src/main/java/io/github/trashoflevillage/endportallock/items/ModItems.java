package io.github.trashoflevillage.endportallock.items;

import io.github.trashoflevillage.endportallock.EndPortalLock;
import io.github.trashoflevillage.endportallock.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EndPortalLock.MOD_ID, name), item);
    }
    
    public static void registerModItems() {
        EndPortalLock.LOGGER.info("Registering items for " + EndPortalLock.MOD_ID + ".");

        addItemsToItemGroup(ItemGroups.FUNCTIONAL,
                ModBlocks.CUSTOM_PORTAL_FRAME1,
                ModBlocks.CUSTOM_PORTAL_FRAME2,
                ModBlocks.CUSTOM_PORTAL_FRAME3,
                ModBlocks.CUSTOM_PORTAL_FRAME4,
                ModBlocks.CUSTOM_PORTAL_FRAME5,
                ModBlocks.CUSTOM_PORTAL_FRAME6,
                ModBlocks.CUSTOM_PORTAL_FRAME7,
                ModBlocks.CUSTOM_PORTAL_FRAME8,
                ModBlocks.CUSTOM_PORTAL_FRAME9,
                ModBlocks.CUSTOM_PORTAL_FRAME10,
                ModBlocks.CUSTOM_PORTAL_FRAME11,
                ModBlocks.CUSTOM_PORTAL_FRAME12
        );
    }

    private static void addItemsToItemGroup(RegistryKey<ItemGroup> group, ItemConvertible... items) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            for (ItemConvertible i : items) content.add(i);
        });
    }
}