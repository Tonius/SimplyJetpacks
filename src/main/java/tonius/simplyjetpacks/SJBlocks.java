package tonius.simplyjetpacks;

import net.minecraft.block.Block;
import tonius.simplyjetpacks.block.BlockUeberCharger;
import tonius.simplyjetpacks.config.MainConfig;
import cpw.mods.fml.common.registry.GameRegistry;

public class SJBlocks {

    public static Block ueberCharger = null;

    public static void preInit() {
        constructBlocks();
        registerBlocks();
    }

    public static void constructBlocks() {
        SimplyJetpacks.logger.info("Constructing blocks");

        ueberCharger = new BlockUeberCharger(MainConfig.ueberChargerID);
    }

    public static void registerBlocks() {
        SimplyJetpacks.logger.info("Registering blocks");

        GameRegistry.registerBlock(ueberCharger, "ueberCharger");
    }

}
