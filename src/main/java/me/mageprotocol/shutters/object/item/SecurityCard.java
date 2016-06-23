package me.mageprotocol.shutters.object.item;

import me.mageprotocol.shutters.object.MPItem;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Zac on 05/02/2016.
 */
public class SecurityCard extends MPItem
{
    private final String name = "securityCard";

    public SecurityCard()
    {
        setUnlocalizedName("securityCard");
    }

    public String getName()
    {
        return name;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("keyOwner", playerIn.getDisplayNameString());
            stack.getTagCompound().setString("keycoardKey", generateRandomKey());
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if(stack.getTagCompound() != null)
        {
            tooltip.add("Keycard Owner: " + stack.getTagCompound().getString("keyOwner"));
            tooltip.add("Keycode: " + stack.getTagCompound().getString("keycardKey"));
        }
        else
        {
            tooltip.add("Keycard Inactive!");
        }
    }

    public String generateRandomKey()
    {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
