package net.azowata.oregenerator;

import net.azowata.oregenerator.containers.ContainerOreGenerator;
import net.azowata.oregenerator.gui.GuiOreGenerator;
import net.azowata.oregenerator.tileentities.TileEntityOreGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int BlockOreGeneratorContainer = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case BlockOreGeneratorContainer:
                return new ContainerOreGenerator(player.inventory, (TileEntityOreGenerator) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case BlockOreGeneratorContainer:
                return new GuiOreGenerator(player.inventory, (TileEntityOreGenerator) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
}
