package railwaytoheaven;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import proxy.CommonProxy;

@Mod(modid = RailwayToHeaven.MODID, name = RailwayToHeaven.NAME, version = RailwayToHeaven.VERSION)
public class RailwayToHeaven
{
    public static final String MODID = "railway_to_heaven";
    public static final String NAME = "Railway to heaven";
    public static final String VERSION = "1.0";

    @Mod.Instance(RailwayToHeaven.MODID)
    public static RailwayToHeaven instance;

    @SidedProxy(serverSide = "proxy.ServerProxy", clientSide = "proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
   
    }
}
