package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.item.IModeSwitchable;
import tonius.simplyjetpacks.item.IToggleable;
import tonius.simplyjetpacks.setup.ModControls;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageModKey implements IMessage, IMessageHandler<MessageModKey, IMessage> {
    
    public int keyId;
    public boolean sneakChangesToggleBehavior;
    public boolean showInChat;
    
    public MessageModKey() {
    }
    
    public MessageModKey(ModControls key, boolean sneakChangesToggleBehavior, boolean showInChat) {
        this.keyId = key.ordinal();
        this.sneakChangesToggleBehavior = sneakChangesToggleBehavior;
        this.showInChat = showInChat;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.keyId = buf.readInt();
        this.sneakChangesToggleBehavior = buf.readBoolean();
        this.showInChat = buf.readBoolean();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.keyId);
        buf.writeBoolean(this.sneakChangesToggleBehavior);
        buf.writeBoolean(this.showInChat);
    }
    
    @Override
    public IMessage onMessage(MessageModKey msg, MessageContext ctx) {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
        
        if (entityPlayer != null) {
            ItemStack armor = entityPlayer.inventory.armorItemInSlot(2);
            if (armor != null) {
                if (msg.keyId == ModControls.TOGGLE.ordinal() && armor.getItem() instanceof IToggleable) {
                    ((IToggleable) armor.getItem()).toggle(armor, entityPlayer, msg.sneakChangesToggleBehavior, msg.showInChat);
                } else if (msg.keyId == ModControls.MODE.ordinal() && armor.getItem() instanceof IModeSwitchable) {
                    ((IModeSwitchable) armor.getItem()).switchMode(armor, entityPlayer, msg.sneakChangesToggleBehavior, msg.showInChat);
                }
            }
        }
        return null;
    }
    
}
