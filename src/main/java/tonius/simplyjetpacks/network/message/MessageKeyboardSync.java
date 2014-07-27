package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import tonius.simplyjetpacks.SyncTracker;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyboardSync implements IMessage, IMessageHandler<MessageKeyboardSync, IMessage> {

    public boolean jumpState;
    public boolean forwardState;
    public boolean backwardState;
    public boolean leftState;
    public boolean rightState;

    public MessageKeyboardSync() {
    }

    public MessageKeyboardSync(boolean jumpState, boolean forwardState, boolean backwardState, boolean leftState, boolean rightState) {
        this.jumpState = jumpState;
        this.forwardState = forwardState;
        this.backwardState = backwardState;
        this.leftState = leftState;
        this.rightState = rightState;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.jumpState = buf.readBoolean();
        this.forwardState = buf.readBoolean();
        this.backwardState = buf.readBoolean();
        this.leftState = buf.readBoolean();
        this.rightState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.jumpState);
        buf.writeBoolean(this.forwardState);
        buf.writeBoolean(this.backwardState);
        buf.writeBoolean(this.leftState);
        buf.writeBoolean(this.rightState);
    }

    @Override
    public IMessage onMessage(MessageKeyboardSync msg, MessageContext ctx) {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
        if (entityPlayer != null) {
            SyncTracker.processKeyUpdate(entityPlayer, msg.jumpState, msg.forwardState, msg.backwardState, msg.leftState, msg.rightState);
        }
        return null;
    }
}
