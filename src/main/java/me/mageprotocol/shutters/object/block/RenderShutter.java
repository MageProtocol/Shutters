package me.mageprotocol.shutters.object.block;

import me.mageprotocol.shutters.object.MPBlock;
import me.mageprotocol.shutters.object.block.tileentity.TileEntityRenderShutter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Zac on 07/02/2016.
 */
public class RenderShutter extends MPBlock implements ITileEntityProvider
{

    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 1);

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }

    private final String name = "renderShutter";

    public RenderShutter()
    {
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName("renderShutter");
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRenderShutter();
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRenderShutter();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
        if(enumfacing == EnumFacing.NORTH || enumfacing == EnumFacing.SOUTH)
        {
            this.getDefaultState().withProperty(TYPE, 0);
        } else
        {
            this.getDefaultState().withProperty(TYPE, 1);
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
                                     int meta, EntityLivingBase placer) {

        return worldIn.getBlockState(pos);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta);
        if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)
        {
            return this.getDefaultState().withProperty(TYPE, 0);
        } else
        {
            return this.getDefaultState().withProperty(TYPE, 1);
        }

    }

    @Override
    public int getMetaFromState(IBlockState state) {


        return ((Integer)state.getValue(TYPE)).intValue();
    }


}
