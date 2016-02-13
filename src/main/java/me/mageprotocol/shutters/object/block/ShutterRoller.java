package me.mageprotocol.shutters.object.block;

import me.mageprotocol.shutters.object.MPBlock;
import me.mageprotocol.shutters.object.ModObjects;
import me.mageprotocol.shutters.object.block.tileentity.TileEntityRenderShutter;
import me.mageprotocol.shutters.object.block.tileentity.TileEntityShutterRoller;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Zac on 12/02/2016.
 */
public class ShutterRoller extends MPBlock implements ITileEntityProvider
{

    public static final PropertyInteger TYPE = PropertyInteger.create("rotation", 0, 1);

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }

    private final String name = "shutterRoller";
    private boolean isActive = false;

    public ShutterRoller()
    {
        GameRegistry.registerBlock(this, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        setUnlocalizedName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityShutterRoller();
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRenderShutter();
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

    /*@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        System.out.println("onBlockPlaced");
        return worldIn.getBlockState(pos);
    }*/


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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        if(!worldIn.isRemote) {
            if (!isActive) {
                isActive = !isActive;
                placeShutters(worldIn, pos, playerIn, state);
                System.out.println("State: Active");
            } else {
                isActive = !isActive;
                removeShutters(worldIn, pos, playerIn);
                System.out.println("State: Inactive");
            }
        }

        return false;
    }

    public void placeShutters(World worldIn, BlockPos pos, EntityPlayer playerIn, IBlockState state)
    {
        for(int i = 1; i < 10; i++) {
            Block render = ModObjects.renderShutter;
            BlockPos position = new BlockPos(pos.getX(), pos.getY() - i, pos.getZ());
            IBlockState blockState = render.getDefaultState();

            if (worldIn.getBlockState(position).getBlock().isAir(worldIn, position)) {
                worldIn.setBlockState(position, blockState);
                RenderShutter renderShutter = (RenderShutter) worldIn.getBlockState(position).getBlock();

                worldIn.setBlockState(position, blockState.withProperty(renderShutter.TYPE, state.getValue(TYPE).intValue()), 2);

            }
        }
    }

    public void removeShutters(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        for(int i = 1; i < 10; i++) {

            BlockPos position = new BlockPos(pos.getX(), pos.getY() - i, pos.getZ());
            if(worldIn.getBlockState(position).getBlock() == ModObjects.renderShutter) {
                worldIn.setBlockToAir(position);
            }
        }
    }
}
