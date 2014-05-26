package tonius.simplyjetpacks;

import net.minecraft.block.Block;
import tonius.simplyjetpacks.block.BlockUeberCharger;
import tonius.simplyjetpacks.config.MainConfig;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJBlocks {

    public static Block ueberCharger = null;

    public static void preInit() {
        constructBlocks();
    }

    public static void constructBlocks() {
        SimplyJetpacks.logger.info("Constructing blocks");

        ueberCharger = new BlockUeberCharger(MainConfig.ueberChargerID);
        GameRegistry.registerBlock(ueberCharger, "ueberCharger");
    }

}
