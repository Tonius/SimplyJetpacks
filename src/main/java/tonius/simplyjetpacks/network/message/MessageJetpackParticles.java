package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.jetpack.JetpackParticleType;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageJetpackParticles implements IMessage, IMessageHandler<MessageJetpackParticles, IMessage> {

    public int entityId;
    public int particleId;

    public MessageJetpackParticles() {
    }

    public MessageJetpackParticles(int entityId, JetpackParticleType particle) {
        this.entityId = entityId;
        this.particleId = particle.ordinal();
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
    public IMessage onMessage(MessageJetpackParticles msg, MessageContext ctx) {
        if (SJConfig.enableJetpackParticles) {
            SimplyJetpacks.proxy.showJetpackParticles(msg.entityId, msg.particleId);
        }
        return null;
    }
}
