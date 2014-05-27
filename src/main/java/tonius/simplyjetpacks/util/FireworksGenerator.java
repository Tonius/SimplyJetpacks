package tonius.simplyjetpacks.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class FireworksGenerator {

    public static enum FireworkType {
        BALL, LARGE_BALL, STAR, CREEPER, BURST;
        private FireworkType() {
        }
    }

    public static class Firework {

        int flight = 0;
        boolean flicker = false;
        boolean trail = false;
        ArrayList<Integer> colors = new ArrayList<Integer>();
        FireworkType type = FireworkType.BALL;

        public Firework setFlight(int duration) {
            if (duration >= 0 && duration <= 3) {
                this.flight = duration;
            }
            return this;
        }

        public Firework setFlicker() {
            this.flicker = true;
            return this;
        }

        public Firework setTrail() {
            this.trail = true;
            return this;
        }

        public Firework setType(FireworkType type) {
            this.type = type;
            return this;
        }

        public Firework setType(int type) {
            if (type >= 0 && type <= 4) {
                this.type = FireworkType.values()[type];
            }
            return this;
        }

        public Firework addColor(int red, int green, int blue) {
            this.colors.add((red << 16) + (green << 8) + blue);
            return this;
        }

        private NBTTagCompound getTags() {
            NBTTagCompound explosionTag = new NBTTagCompound();

            explosionTag.setBoolean("Flicker", this.flicker);
            explosionTag.setBoolean("Trail", this.trail);

            explosionTag.setByte("Type", (byte) this.type.ordinal());

            int[] intArray = new int[this.colors.size()];
            for (int i = 0; i < this.colors.size(); i++) {
                intArray[i] = this.colors.get(i);
            }
            explosionTag.setIntArray("Colors", intArray);

            return explosionTag;
        }

        public ItemStack getStack() {
            NBTTagCompound tags = new NBTTagCompound();

            NBTTagCompound fireworksTag = new NBTTagCompound();
            NBTTagList explosionsList = new NBTTagList();
            explosionsList.appendTag(this.getTags());

            fireworksTag.setByte("Flight", (byte) this.flight);
            fireworksTag.setTag("Explosions", explosionsList);
            tags.setCompoundTag("Fireworks", fireworksTag);

            ItemStack stack = new ItemStack(Item.firework);
            stack.setTagCompound(tags);
            return stack;
        }

    }

    public static ItemStack randomFirework() {
        Random rand = new Random();

        Firework firework = new Firework();

        switch (rand.nextInt(3)) {
        case 0:
            firework.setFlicker();
            break;
        case 1:
            firework.setTrail();
        }

        int type = rand.nextInt(5);
        firework.setType(type);

        for (int i = 0; i <= rand.nextInt(6); i++) {
            Color randomColor = ColorUtils.getRandomColor();
            firework.addColor(randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue());
        }

        return firework.getStack();
    }

}
