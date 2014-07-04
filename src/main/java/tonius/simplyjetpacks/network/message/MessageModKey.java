package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SJKey;
import tonius.simplyjetpacks.item.ItemEnergyArmor;
import tonius.simplyjetpacks.item.jetpack.ItemJetpack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageModKey implements IMessage, IMessageHandler<MessageModKey, IMessage> {

    public int keyId;

    public MessageModKey() {
    }

    public MessageModKey(SJKey key) {
        this.keyId = key.ordinal();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.keyId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.keyId);
    }

    @Override
    public IMessage onMessage(MessageModKey msg, MessageContext ctx) {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null) {
            if (msg.keyId == SJKey.TOGGLE.ordinal()) {
                ItemStack armorEnergy = entityPlayer.inventory.armorItemInSlot(2);
                if (armorEnergy != null && armorEnergy.getItem() instanceof ItemEnergyArmor) {
                    ((ItemEnergyArmor) armorEnergy.getItem()).toggle(armorEnergy, entityPlayer);
                }
            } else if (msg.keyId == SJKey.MODE.ordinal()) {
                ItemStack jetpack = entityPlayer.inventory.armorItemInSlot(2);
                if (jetpack != null && jetpack.getItem() instanceof ItemJetpack) {
                    ((ItemJetpack) jetpack.getItem()).toggleHoverMode(jetpack, entityPlayer);
                }
            }
        }
        return null;
    }

}
