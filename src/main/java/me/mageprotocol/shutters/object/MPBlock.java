package me.mageprotocol.shutters.object;

import me.mageprotocol.shutters.Shutters;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Zac on 05/02/2016.
 */
public class MPBlock extends Block
{

    public MPBlock()
    {
        super(Material.rock);
        setCreativeTab(ModObjects.shuttersTab);
    }
}
