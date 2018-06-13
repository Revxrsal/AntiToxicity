package net.reflxction.antitoxicity;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.reflxction.antitoxicity.utils.Reference;

import java.io.File;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class AntiToxicity {

    private Configuration config;

    private boolean isEnabled = true;

    /**
     * Initialize variables here
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(new File("config/antitoxicity.cfg"));
        isEnabled = config.get("Enabled", "Enabled", true).getBoolean();
    }

    /**
     * Register events here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Register commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartedEvent event) {

    }


    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean b) {
        isEnabled = b;
    }

    public Configuration getConfig() {
        return config;
    }

}
