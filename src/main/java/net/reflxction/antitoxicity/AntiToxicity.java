package net.reflxction.antitoxicity;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.reflxction.antitoxicity.commands.ATCommand;
import net.reflxction.antitoxicity.listeners.KeyListener;
import net.reflxction.antitoxicity.listeners.RenderListener;
import net.reflxction.antitoxicity.utils.Reference;

import java.io.File;

/**
 * A mod which aims on reducing toxicity, by closing the chat box when any offensive word is typed in it.
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class AntiToxicity {

    private static Configuration config;

    static {
        config = new Configuration(new File("config/anti-toxicity.cfg"));
    }

    public boolean enabled = true;

    /**
     * Initialize variables here
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(new File("config/anti-toxicity.cfg"));
        enabled = config.get("All", "Enabled", true).getBoolean();
    }

    /**
     * Register events and client commands here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new KeyListener());
        MinecraftForge.EVENT_BUS.register(new RenderListener());
        ClientCommandHandler.instance.registerCommand(new ATCommand());
    }

    /**
     * Register commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new ATCommand());
    }


    public boolean isEnabled() {
        return enabled;
    }


    public Configuration getConfig() {
        return config;
    }

}
