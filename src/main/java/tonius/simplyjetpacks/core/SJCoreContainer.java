package tonius.simplyjetpacks.core;

import java.util.Arrays;
import java.util.Map;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class SJCoreContainer extends DummyModContainer implements IFMLLoadingPlugin {

    public SJCoreContainer() {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "simplyjetpackscore";
        meta.name = "Simply Jetpacks Coremod";
        meta.version = "1.0";
        meta.authorList = Arrays.asList("tonius11");
        meta.description = "Hacks that are needed for Simply Jetpacks to do certain things.";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return "tonius.simplyjetpacks.core.SJCoreContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return "tonius.simplyjetpacks.core.SJAccessTransformers";
    }

}
