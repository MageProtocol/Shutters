package me.mageprotocol.shutters.object;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by Zac on 06/02/2016.
 */
public class ShuttersCreativeTab extends CreativeTabs {

    public ShuttersCreativeTab(int nextID, String shuttersTab) {

        super(nextID, shuttersTab);
    }

    @Override
    public Item getTabIconItem() {
        return Items.APPLE;
    }
}
