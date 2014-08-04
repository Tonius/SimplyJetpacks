package tonius.simplyjetpacks.nei;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StringUtils;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class DeArmoringRecipeHandler extends TemplateRecipeHandler {

    @Override
    public String getRecipeName() {
        return StringUtils.translate("gui.nei.recipe.armoring");
    }

    @Override
    public String getGuiTexture() {
        return "simplyjetpacks:textures/gui/nei/armoring.png";
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(6, 31, 0, 0, 13, 20);
        GuiDraw.drawTexturedModalRect(6, 73, 13, 0, 13, 13);
    }

    @Override
    public void drawForeground(int recipe) {
        super.drawForeground(recipe);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.input"), 26, 16, 0x404040, false);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.output"), 26, 58, 0x404040, false);
        GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.plating"), 26, 94, 0x404040, false);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        int dmg = result.getItemDamage();
        if (result.getItem() == SJItems.jetpacks) {
            Jetpack jetpack = Jetpack.getJetpack(dmg);
            if (jetpack != null && !jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, dmg + 100), new ItemStack(SJItems.jetpacks, 1, dmg), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta())));
            }
        } else if (result.getItem() == SJItems.fluxpacks) {
            FluxPack fluxpack = FluxPack.getFluxPack(dmg);
            if (fluxpack != null && !fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, dmg + 100), new ItemStack(SJItems.fluxpacks, 1, dmg), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta())));
            }
        } else if (result.getItem() == SJItems.components) {
            for (int i = 0; i <= Jetpack.getHighestMeta(); i++) {
                Jetpack jetpack = Jetpack.getJetpack(i);
                if (jetpack != null && jetpack.isArmored() && jetpack.hasArmoredVersion() && jetpack.getPlatingMeta() == dmg) {
                    this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, i), new ItemStack(SJItems.jetpacks, 1, i - 100), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta())));
                }
            }
            for (int i = 0; i <= FluxPack.getHighestMeta(); i++) {
                FluxPack fluxpack = FluxPack.getFluxPack(i);
                if (fluxpack != null && fluxpack.isArmored() && fluxpack.hasArmoredVersion() && fluxpack.getPlatingMeta() == dmg) {
                    this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, i), new ItemStack(SJItems.fluxpacks, 1, i - 100), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta())));
                }
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient.getItem() == SJItems.jetpacks) {
            int dmg = ingredient.getItemDamage();
            Jetpack jetpack = Jetpack.getJetpack(dmg);
            if (jetpack != null && jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.jetpacks, 1, dmg), new ItemStack(SJItems.jetpacks, 1, dmg - 100), new ItemStack(SJItems.components, 1, jetpack.getPlatingMeta())));
            }
        } else if (ingredient.getItem() == SJItems.fluxpacks) {
            int dmg = ingredient.getItemDamage();
            FluxPack fluxpack = FluxPack.getFluxPack(dmg);
            if (fluxpack != null && fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(SJItems.fluxpacks, 1, dmg), new ItemStack(SJItems.fluxpacks, 1, dmg - 100), new ItemStack(SJItems.components, 1, fluxpack.getPlatingMeta())));
            }
        }
    }

    public class CachedDeArmoringRecipe extends CachedRecipe {

        private PositionedStack input;
        private PositionedStack output;
        private PositionedStack plating;

        public CachedDeArmoringRecipe(ItemStack input, ItemStack output, ItemStack plating) {
            this.input = new PositionedStack(input, 4, 13);
            this.output = new PositionedStack(output, 4, 53);
            this.plating = new PositionedStack(plating, 4, 89);
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
