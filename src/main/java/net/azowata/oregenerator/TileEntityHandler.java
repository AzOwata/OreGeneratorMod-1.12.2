package net.azowata.oregenerator;

import net.azowata.oregenerator.tileentities.TileEntityOreGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityOreGenerator.class, new ResourceLocation(OreGenerator.MODID, "ore_generator"));
    }
}
