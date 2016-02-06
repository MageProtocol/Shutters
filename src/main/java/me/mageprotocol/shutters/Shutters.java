package me.mageprotocol.shutters;

import me.mageprotocol.shutters.handler.GuiHandler;
import me.mageprotocol.shutters.object.ModObjects;
import me.mageprotocol.shutters.object.ShuttersCreativeTab;
import me.mageprotocol.shutters.proxy.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Zac on 05/02/2016.
 */

@Mod(modid="shutters", name = "shutters", version = "2.0.a")
public class Shutters
{



    @SidedProxy(clientSide = "me.mageprotocol.shutters.proxy.ClientProxy", serverSide = "me.mageprotocol.shutters.proxy.CommonProxy")
    public static IProxy proxy;

    @Mod.Instance
    public static Shutters instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        ModObjects.init();
        MinecraftForge.EVENT_BUS.register(new GuiHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        if(event.getSide() == Side.CLIENT)
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            renderItem.getItemModelMesher().register(ModObjects.securityCard, 0, new ModelResourceLocation("shutters:securityCard", "inventory"));
        }
    }


}
