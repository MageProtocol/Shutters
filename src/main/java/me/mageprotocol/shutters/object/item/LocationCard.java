package me.mageprotocol.shutters.object.item;

import me.mageprotocol.shutters.object.MPItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by Zac on 07/02/2016.
 */
public class LocationCard extends MPItem
{

    private final String name = "locationCard";

    public LocationCard()
    {
        GameRegistry.registerItem(this, name);
        setUnlocalizedName("MPS:locationCard");
    }

    public String getName()
    {
        return name;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {

        if(playerIn.isSneaking()) {
            if (itemStackIn.getTagCompound() == null) {
                itemStackIn.setTagCompound(new NBTTagCompound());
            }


            //itemStackIn.getTagCompound().setInteger("locationX", mop.getBlockPos().getX());
            //itemStackIn.getTagCompound().setInteger("locationY", mop.getBlockPos().getX());
            //itemStackIn.getTagCompound().setInteger("locationZ", mop.getBlockPos().getX());
        }

        return itemStackIn;
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
