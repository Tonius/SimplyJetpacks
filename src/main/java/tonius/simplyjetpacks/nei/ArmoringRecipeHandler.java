package tonius.simplyjetpacks.nei;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StringUtils;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class ArmoringRecipeHandler extends TemplateRecipeHandler {
    
    @Override
    public String getRecipeName() {
        return StringUtils.translate("gui.nei.recipe.armoring");
    }
    
    @Override
    public String getGuiTexture() {
        return SimplyJetpacks.RESOURCE_PREFIX + "textures/gui/nei/armoring.png";
    }
    
    @Override
    public int recipiesPerPage() {
        return 1;
    }
    
    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(6, 32, 13, 0, 13, 13);
        GuiDraw.drawTexturedModalRect(6, 67, 0, 0, 13, 20);
    }
    
    @Override
    public void drawForeground(int recipe) {
        super.drawForeground(recipe);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.input"), 26, 17, 0x404040, false);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.plating"), 26, 52, 0x404040, false);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.output"), 26, 94, 0x404040, false);
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        int dmg = result.getItemDamage();
        if (result.getItem() == SJItems.jetpacks) {
            Jetpack jetpack = Jetpack.getJetpack(dmg);
            if (jetpack != null && jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, dmg - 100), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta()), new ItemStack(SJItems.jetpacks, 1, dmg)));
            }
        } else if (result.getItem() == SJItems.fluxpacks) {
            FluxPack fluxpack = FluxPack.getFluxPack(dmg);
            if (fluxpack != null && fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, dmg - 100), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta()), new ItemStack(SJItems.fluxpacks, 1, dmg)));
            }
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        int dmg = ingredient.getItemDamage();
        if (ingredient.getItem() == SJItems.jetpacks) {
            Jetpack jetpack = Jetpack.getJetpack(dmg);
            if (jetpack != null && !jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, dmg), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta()), new ItemStack(SJItems.jetpacks, 1, dmg + 100)));
            }
        } else if (ingredient.getItem() == SJItems.fluxpacks) {
            FluxPack fluxpack = FluxPack.getFluxPack(dmg);
            if (fluxpack != null && !fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, dmg), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta()), new ItemStack(SJItems.fluxpacks, 1, dmg + 100)));
            }
        } else if (ingredient.getItem() == SJItems.components) {
            for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
                Jetpack jetpack = Jetpack.getJetpack(i);
                if (jetpack != null && !jetpack.isArmored() && jetpack.hasArmoredVersion() && jetpack.getPlatingMeta() == dmg) {
                    this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, i), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta()), new ItemStack(SJItems.jetpacks, 1, i + 100)));
                }
            }
            for (int i = 0; i <= FluxPack.getHighestMeta(); i++) {
                FluxPack fluxpack = FluxPack.getFluxPack(i);
                if (fluxpack != null && !fluxpack.isArmored() && fluxpack.hasArmoredVersion() && fluxpack.getPlatingMeta() == dmg) {
                    this.arecipes.add(new CachedArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, i), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta()), new ItemStack(SJItems.fluxpacks, 1, i + 100)));
                }
            }
        }
    }
    
    public class CachedArmoringRecipe extends CachedRecipe {
        
        private PositionedStack input;
        private PositionedStack plating;
        private PositionedStack output;
        
        public CachedArmoringRecipe(ItemStack input, ItemStack plating, ItemStack output) {
            this.input = new PositionedStack(input, 4, 13);
            this.plating = new PositionedStack(plating, 4, 48);
            this.output = new PositionedStack(output, 4, 90);
        }
        
        @Override
        public PositionedStack getIngredient() {
            return this.input;
        }
        
        @Override
        public PositionedStack getOtherStack() {
            return this.plating;
        }
        
        @Override
        public PositionedStack getResult() {
            return this.output;
        }
        
    }
    
}
