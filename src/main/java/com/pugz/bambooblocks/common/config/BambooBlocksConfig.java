package com.pugz.bambooblocks.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.pugz.bambooblocks.common.block.BambooPressurePlateBlock;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class BambooBlocksConfig {

    public static final String CONFIG = "bambooblocks";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue bambooTorches;
    public static ForgeConfigSpec.BooleanValue bambooPlanks;
    public static ForgeConfigSpec.BooleanValue reedThatch;
    //public static ForgeConfigSpec.BooleanValue directionalThatch;
    public static ForgeConfigSpec.IntValue bambooButtonLength;
    public static ForgeConfigSpec.EnumValue<BambooPressurePlateBlock.Sensitivity> bambooPressurePlateSensitivity;
    public static ForgeConfigSpec.IntValue bambooTorchBurnTime;

    static {
        COMMON_BUILDER.comment("Bamboo Blocks Settings").push(CONFIG);
        setupFirstBlockConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void setupFirstBlockConfig() {
        bambooTorches = COMMON_BUILDER.comment("Toggle Bamboo Torches").define("bamboo_torches", true);
        bambooPlanks = COMMON_BUILDER.comment("Toggle Bamboo Torches").define("bamboo_planks", true);
        reedThatch = COMMON_BUILDER.comment("Toggle Reed Thatch").define("reed_thatch", true);
        bambooButtonLength = COMMON_BUILDER.comment("Set length of Bamboo Button pulse in seconds").defineInRange("ticks", 2, 0, Integer.MAX_VALUE);
        bambooPressurePlateSensitivity = COMMON_BUILDER.comment("Set Sensitivity of Bamboo Pressure Plates").defineEnum("sensitivity", BambooPressurePlateBlock.Sensitivity.ALL);
        bambooTorchBurnTime = COMMON_BUILDER.comment("Set fuel burn time of Bamboo Torches").defineInRange("burn_time", 50, -1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}