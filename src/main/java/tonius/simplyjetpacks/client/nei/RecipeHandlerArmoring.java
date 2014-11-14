package tonius.simplyjetpacks.client.nei;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemIndex;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.setup.SJItems;
import tonius.simplyjetpacks.util.StringUtils;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RecipeHandlerArmoring extends TemplateRecipeHandler {
    
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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getGuiTexture());
        if (this.arecipes.get(recipe) instanceof CachedArmoringRecipe) {
            GuiDraw.drawTexturedModalRect(6, 32, 13, 0, 13, 13);
            GuiDraw.drawTexturedModalRect(6, 67, 0, 0, 13, 20);
        } else {
            GuiDraw.drawTexturedModalRect(6, 31, 0, 0, 13, 20);
            GuiDraw.drawTexturedModalRect(6, 73, 13, 0, 13, 13);
        }
    }
    
    @Override
    public void drawForeground(int recipe) {
        super.drawForeground(recipe);
        if (this.arecipes.get(recipe) instanceof CachedArmoringRecipe) {
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.input"), 26, 17, 0x404040, false);
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.plating"), 26, 52, 0x404040, false);
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.armoring.output"), 26, 94, 0x404040, false);
        } else {
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.input"), 26, 16, 0x404040, false);
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.output"), 26, 58, 0x404040, false);
            GuiDraw.drawString(StringUtils.translate("gui.nei.recipe.dearmoring.plating"), 26, 94, 0x404040, false);
        }
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Jetpack jetpack = getJetpack(result);
        if (jetpack != null && jetpack.hasArmoredVersion()) {
            int platingMeta = ((ItemJetpack) result.getItem()).modType.platingOffset + jetpack.tier;
            if (jetpack.isArmored()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(result.getItem(), 1, result.getItemDamage() - Jetpack.ARMORED_META_OFFSET), new ItemStack(SJItems.armorPlatings, 1, platingMeta), new ItemStack(result.getItem(), 1, result.getItemDamage())));
            } else {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(result.getItem(), 1, result.getItemDamage() + Jetpack.ARMORED_META_OFFSET), new ItemStack(result.getItem(), 1, result.getItemDamage()), new ItemStack(SJItems.armorPlatings, 1, platingMeta)));
            }
        }
        
        FluxPack fluxpack = getFluxPack(result);
        if (fluxpack != null && fluxpack.hasArmoredVersion()) {
            int platingMeta = ((ItemFluxPack) result.getItem()).modType.platingOffset + fluxpack.tier - 1;
            if (fluxpack.isArmored()) {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(result.getItem(), 1, result.getItemDamage() - FluxPack.ARMORED_META_OFFSET), new ItemStack(SJItems.armorPlatings, 1, platingMeta), new ItemStack(result.getItem(), 1, result.getItemDamage())));
            } else {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(result.getItem(), 1, result.getItemDamage() + FluxPack.ARMORED_META_OFFSET), new ItemStack(result.getItem(), 1, result.getItemDamage()), new ItemStack(SJItems.armorPlatings, 1, platingMeta)));
            }
        }
        
        if (result.getItem() == SJItems.armorPlatings) {
            for (int i = 0; i <= Jetpack.getHighestMeta(ItemIndex.PER_MOD); i++) {
                jetpack = Jetpack.getJetpack(ItemIndex.PER_MOD, i);
                if (jetpack != null && jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                    for (ItemJetpack jetpacksItem : SJItems.jetpacksesPerMod) {
                        int platingMeta = jetpacksItem.modType.platingOffset + jetpack.tier;
                        if (platingMeta == result.getItemDamage()) {
                            this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(jetpacksItem, 1, i), new ItemStack(jetpacksItem, 1, i - Jetpack.ARMORED_META_OFFSET), result));
                        }
                    }
                }
            }
            for (int i = 0; i <= FluxPack.getHighestMeta(ItemIndex.PER_MOD); i++) {
                fluxpack = FluxPack.getFluxPack(ItemIndex.PER_MOD, i);
                if (fluxpack != null && fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                    for (ItemFluxPack fluxpacksItem : SJItems.fluxpacksesPerMod) {
                        int platingMeta = fluxpacksItem.modType.platingOffset + fluxpack.tier - 1;
                        if (platingMeta == result.getItemDamage()) {
                            this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(fluxpacksItem, 1, i), new ItemStack(fluxpacksItem, 1, i - FluxPack.ARMORED_META_OFFSET), result));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Jetpack jetpack = getJetpack(ingredient);
        if (jetpack != null && jetpack.hasArmoredVersion()) {
            int platingMeta = ((ItemJetpack) ingredient.getItem()).modType.platingOffset + jetpack.tier;
            if (jetpack.isArmored()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() - Jetpack.ARMORED_META_OFFSET), new ItemStack(SJItems.armorPlatings, 1, platingMeta)));
            } else {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(SJItems.armorPlatings, 1, platingMeta), new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() + Jetpack.ARMORED_META_OFFSET)));
            }
        }
        
        FluxPack fluxpack = getFluxPack(ingredient);
        if (fluxpack != null && fluxpack.hasArmoredVersion()) {
            int platingMeta = ((ItemFluxPack) ingredient.getItem()).modType.platingOffset + fluxpack.tier - 1;
            if (fluxpack.isArmored()) {
                this.arecipes.add(new CachedDeArmoringRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() - FluxPack.ARMORED_META_OFFSET), new ItemStack(SJItems.armorPlatings, 1, platingMeta)));
            } else {
                this.arecipes.add(new CachedArmoringRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(SJItems.armorPlatings, 1, platingMeta), new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() + FluxPack.ARMORED_META_OFFSET)));
            }
        }
        
        if (ingredient.getItem() == SJItems.armorPlatings) {
            for (int i = 0; i <= Jetpack.getHighestMeta(ItemIndex.PER_MOD); i++) {
                jetpack = Jetpack.getJetpack(ItemIndex.PER_MOD, i);
                if (jetpack != null && !jetpack.isArmored() && jetpack.hasArmoredVersion()) {
                    for (ItemJetpack jetpacksItem : SJItems.jetpacksesPerMod) {
                        int platingMeta = jetpacksItem.modType.platingOffset + jetpack.tier;
                        if (platingMeta == ingredient.getItemDamage()) {
                            this.arecipes.add(new CachedArmoringRecipe(new ItemStack(jetpacksItem, 1, i), ingredient, new ItemStack(jetpacksItem, 1, i + Jetpack.ARMORED_META_OFFSET)));
                        }
                    }
                }
            }
            for (int i = 0; i <= FluxPack.getHighestMeta(ItemIndex.PER_MOD); i++) {
                fluxpack = FluxPack.getFluxPack(ItemIndex.PER_MOD, i);
                if (fluxpack != null && !fluxpack.isArmored() && fluxpack.hasArmoredVersion()) {
                    for (ItemFluxPack fluxpacksItem : SJItems.fluxpacksesPerMod) {
                        int platingMeta = fluxpacksItem.modType.platingOffset + fluxpack.tier - 1;
                        if (platingMeta == ingredient.getItemDamage()) {
                            this.arecipes.add(new CachedArmoringRecipe(new ItemStack(fluxpacksItem, 1, i), ingredient, new ItemStack(fluxpacksItem, 1, i + FluxPack.ARMORED_META_OFFSET)));
                        }
                    }
                }
            }
        }
    }
    
    private static Jetpack getJetpack(ItemStack stack) {
        if (stack.getItem() instanceof ItemJetpack) {
            return ((ItemJetpack) stack.getItem()).getJetpack(stack);
        }
        return null;
    }
    
    private static FluxPack getFluxPack(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluxPack) {
            return ((ItemFluxPack) stack.getItem()).getFluxPack(stack);
        }
        return null;
    }
    
}
