package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import tonius.simplyjetpacks.SyncTracker;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageJetpackSync implements IMessage, IMessageHandler<MessageJetpackSync, IMessage> {
    
    public int entityId;
    public int particleId;
    
    public MessageJetpackSync() {
    }
    
    public MessageJetpackSync(int entityId, int particleId) {
        this.entityId = entityId;
        this.particleId = particleId;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.particleId = buf.readInt();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeInt(this.particleId);
    }
    
    @Override
    public IMessage onMessage(MessageJetpackSync msg, MessageContext ctx) {
        Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(msg.entityId);
        if (entity != null && entity instanceof EntityLivingBase) {
            if (msg.particleId >= 0) {
                JetpackParticleType particle = JetpackParticleType.values()[msg.particleId];
                SyncTracker.processJetpackUpdate(msg.entityId, particle);
            } else {
                SyncTracker.processJetpackUpdate(msg.entityId, null);
            }
        }
        return null;
    }
}
