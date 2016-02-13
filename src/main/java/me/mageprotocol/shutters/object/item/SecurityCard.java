package me.mageprotocol.shutters.object.item;

import me.mageprotocol.shutters.object.MPItem;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        GameRegistry.registerItem(this, name);
        setUnlocalizedName("MPS:securityCard");
    }

    public String getName()
    {
        return name;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if(itemStackIn.getTagCompound() == null)
        {

            itemStackIn.setTagCompound(new NBTTagCompound());
            itemStackIn.getTagCompound().setString("keyOwner", playerIn.getName());
            itemStackIn.getTagCompound().setString("keycardKey", generateRandomKey());

        }

        return itemStackIn;
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
