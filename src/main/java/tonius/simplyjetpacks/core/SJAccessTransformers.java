package tonius.simplyjetpacks.core;

import java.io.IOException;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class SJAccessTransformers extends AccessTransformer {

    public SJAccessTransformers() throws IOException {
        super("simplyjetpacks_at.cfg");
    }

}
