package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMysteriousPotato extends Item {
    
    public ItemMysteriousPotato() {
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + "mysteriousPotato");
        this.setTextureName("potato_poisonous");
        this.setCreativeTab(ModCreativeTab.tab);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        list.add(StringUtils.translate("tooltip.mysteriousPotato.description"));
    }
    
    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.uncommon;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass) {
        return pass == 0;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof TileEntityMobSpawner) {
                NBTTagCompound tag = new NBTTagCompound();
                ((TileEntityMobSpawner) tile).writeToNBT(tag);
                
                tag.setString("EntityId", "Zombie");
                
                NBTTagList spawnPotentials = new NBTTagList();
                NBTTagCompound zombieSpawn = new NBTTagCompound();
                
                zombieSpawn.setString("Type", "Zombie");
                zombieSpawn.setInteger("Weight", 1);
                
                NBTTagCompound zombieSpawnProperties = new NBTTagCompound();
                zombieSpawnProperties.setString("id", "Zombie");
                
                NBTTagList equipment = new NBTTagList();
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(new NBTTagCompound());
                equipment.appendTag(ModItems.jetpackPotato.writeToNBT(new NBTTagCompound()));
                zombieSpawnProperties.setTag("Equipment", equipment);
                
                NBTTagList dropChances = new NBTTagList();
                for (int i = 0; i <= 4; i++) {
                    dropChances.appendTag(new NBTTagFloat(0.0F));
                }
                zombieSpawnProperties.setTag("DropChances", dropChances);
                
                zombieSpawn.setTag("Properties", zombieSpawnProperties);
                spawnPotentials.appendTag(zombieSpawn);
                
                tag.setTag("SpawnPotentials", spawnPotentials);
                
                tag.setShort("SpawnCount", (short) 2);
                tag.setShort("SpawnRange", (short) 8);
                tag.setShort("Delay", (short) -1);
                tag.setShort("MinSpawnDelay", (short) 30);
                tag.setShort("MaxSpawnDelay", (short) 60);
                tag.setShort("MaxNearbyEntities", (short) 10);
                tag.setShort("RequiredPlayerRange", (short) 96);
                ((TileEntityMobSpawner) tile).readFromNBT(tag);
            }
        }
        return true;
    }
    
}
