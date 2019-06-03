package railwaytoheaven;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import proxy.CommonProxy;
import tab.SchemesTab;

@Mod(modid = RailwayToHeaven.MODID, name = RailwayToHeaven.NAME, version = RailwayToHeaven.VERSION)
public class RailwayToHeaven
{
    public static final String MODID = "railway_to_heaven";
    public static final String NAME = "Railway to heaven";
    public static final String VERSION = "1.0";

    @Mod.Instance(RailwayToHeaven.MODID)
    public static RailwayToHeaven instance;
    
    public static final SchemesTab SCHEMES = new SchemesTab();

    @SidedProxy(serverSide = "proxy.ServerProxy", clientSide = "proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    	
    }
    
  
}