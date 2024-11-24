package net.azowata.oregenerator.blocks;

import net.azowata.oregenerator.OreGenerator;
import net.azowata.oregenerator.GuiHandler;
import net.azowata.oregenerator.tileentities.TileEntityOreGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockOreGenerator extends Block implements ITileEntityProvider {
    public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(OreGenerator.MODID, "ore_generator");
    public BlockOreGenerator() {
        super(Material.IRON);
        setTranslationKey(REGISTRY_NAME.getPath());
        setRegistryName(REGISTRY_NAME);
        setHardness(3.0F);
        setResistance(20.0F);
        setSoundType(SoundType.METAL);
        setDefaultState(blockState.getBaseState());
        setCreativeTab(CreativeTabs.REDSTONE);
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.isSneaking()) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity instanceof TileEntityOreGenerator tileEntityOreGenerator) {
                    Minecraft mc = Minecraft.getMinecraft();

                    int oreId = tileEntityOreGenerator.getCurrentOreIndex();
                    tileEntityOreGenerator.setCurrentOreIndex((oreId + 1) % tileEntityOreGenerator.getOreListSize());

                    String oreName = tileEntityOreGenerator.getCurrentOreName();
                    playerIn.sendStatusMessage(new TextComponentString(oreName), true);
                    worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
                }
            } else {
                playerIn.openGui(OreGenerator.INSTANCE, GuiHandler.BlockOreGeneratorContainer, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityOreGenerator();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

        boolean flag = worldIn.isBlockPowered(pos);

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityOreGenerator tileEntityOreGenerator) {
            tileEntityOreGenerator.setActive(flag);
        }
    }
}
