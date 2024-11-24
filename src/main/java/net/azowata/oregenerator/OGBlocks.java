package net.azowata.oregenerator;

import net.azowata.oregenerator.blocks.BlockOreGenerator;
import net.minecraft.block.Block;

public class OGBlocks {
    public static final Block OreGenerator = new BlockOreGenerator();

    private static final Block[] BLOCKS = new Block[] {
        OreGenerator
    };

    public static Block[] getBlocks() {
        return BLOCKS;
    }
}
