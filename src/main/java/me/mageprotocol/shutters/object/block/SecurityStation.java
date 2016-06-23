package me.mageprotocol.shutters.object.block;

import me.mageprotocol.shutters.Shutters;
import me.mageprotocol.shutters.object.MPBlock;
import me.mageprotocol.shutters.object.block.tileentity.TileEntitySecurityStation;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Zac on 05/02/2016.
 */
public class SecurityStation extends MPBlock implements ITileEntityProvider
{

    private final String name = "securityStation";

    public SecurityStation()
    {
        setUnlocalizedName("securityStation");
        setRegistryName("securityStation");
    }

    public String getName()
    {
        return name;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntitySecurityStation();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(Shutters.instance, 1, worldIn, (int) hitX, (int) hitY, (int) hitZ);
        }
        return false;
    }
}
