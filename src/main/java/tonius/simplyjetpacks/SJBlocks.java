package tonius.simplyjetpacks;

import net.minecraft.block.Block;
import tonius.simplyjetpacks.block.BlockSJUeberCharger;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJBlocks {

    public static Block ueberCharger = null;

    public static void preInit() {
        registerBlocks();
    }

    public static void registerBlocks() {
        ueberCharger = new BlockSJUeberCharger(ConfigReader.ueberChargerID);
        GameRegistry.registerBlock(ueberCharger, "ueberCharger");
    }

}
