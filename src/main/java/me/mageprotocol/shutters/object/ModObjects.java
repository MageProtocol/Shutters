package me.mageprotocol.shutters.object;

import me.mageprotocol.shutters.object.block.RenderShutter;
import me.mageprotocol.shutters.object.block.SecurityStation;
import me.mageprotocol.shutters.object.block.ShutterRoller;
import me.mageprotocol.shutters.object.block.tileentity.TileEntityRenderShutter;
import me.mageprotocol.shutters.object.block.tileentity.TileEntitySecurityStation;
import me.mageprotocol.shutters.object.block.tileentity.TileEntityShutterRoller;
import me.mageprotocol.shutters.object.item.LocationCard;
import me.mageprotocol.shutters.object.item.SecurityCard;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zac on 05/02/2016.
 */
public class ModObjects
{

    public static CreativeTabs shuttersTab  = new ShuttersCreativeTab(CreativeTabs.getNextID(), "shuttersTab");
    //Blocks
    public static Block securityStation = new SecurityStation();
    public static Block renderShutter = new RenderShutter();
    public static Block shutterRoller = new ShutterRoller();

    //Items
    public static Item securityCard = new SecurityCard();
    public static Item locationCard = new LocationCard();

    public static void init()
    {
        registerBlocks();
        System.out.println("Registering Recipes");
        registerRecipes();
        System.out.println("Registering Other");
        registerOthers();
    }

    private static void registerBlocks() {
        GameRegistry.register(securityStation);
        GameRegistry.register(new ItemBlock(securityStation), new ResourceLocation("securityStation"));

        GameRegistry.register(renderShutter);
        GameRegistry.register(new ItemBlock(renderShutter), new ResourceLocation("renderShutter"));

        GameRegistry.register(shutterRoller);
        GameRegistry.register(new ItemBlock(shutterRoller), new ResourceLocation("shutterRoller"));
    }

    private static void registerItems() {
        GameRegistry.register(securityCard, new ResourceLocation("securityCard"));
        GameRegistry.register(locationCard, new ResourceLocation("locationCard"));
    }

    public static void registerRecipes()
    {

    }

    public static void registerOthers()
    {
        GameRegistry.registerTileEntity(TileEntitySecurityStation.class, "MPS:TESS");
        GameRegistry.registerTileEntity(TileEntityRenderShutter.class, "MPS:TERS");
        GameRegistry.registerTileEntity(TileEntityShutterRoller.class, "MPS:TESR");
    }

}
