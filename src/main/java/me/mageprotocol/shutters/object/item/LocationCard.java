package me.mageprotocol.shutters.object.item;

import me.mageprotocol.shutters.object.MPItem;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Zac on 07/02/2016.
 */
public class LocationCard extends MPItem
{

    private final String name = "locationCard";

    public LocationCard()
    {
        setUnlocalizedName("locationCard");
    }

    public String getName()
    {
        return name;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            if (stack.getTagCompound() == null) {
                stack.setTagCompound(new NBTTagCompound());
            }

            stack.getTagCompound().setInteger("locationX", pos.getX());
            stack.getTagCompound().setInteger("locationY", pos.getY());
            stack.getTagCompound().setInteger("locationZ", pos.getZ());
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

        if(stack.getTagCompound() != null)
        {
            tooltip.add("Location X: " + stack.getTagCompound().getInteger("locationX"));
            tooltip.add("Location Y: " + stack.getTagCompound().getInteger("locationY"));
            tooltip.add("Location Z: " + stack.getTagCompound().getInteger("locationZ"));
        }
        return;
    }
}
