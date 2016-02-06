package me.mageprotocol.shutters.object.block;

import me.mageprotocol.shutters.Shutters;
import me.mageprotocol.shutters.object.MPBlock;
import me.mageprotocol.shutters.object.block.tileentity.TileEntitySecurityStation;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Zac on 05/02/2016.
 */
public class SecurityStation extends MPBlock implements ITileEntityProvider
{

    private final String name = "securityStation";

    public SecurityStation()
    {
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName("MPS:" + name);
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(Shutters.instance, 1, world, (int)hitX, (int)hitY, (int)hitZ);
        }
        return false;
    }


}
