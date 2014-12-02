package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;

import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.config.FluxPackConfig;
import tonius.simplyjetpacks.config.JetpackConfig;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {
    
    public NBTTagCompound recv;
    
    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound toSend = new NBTTagCompound();
        
        for (Entry<Integer, JetpackConfig> e : Config.jetpackConfigs.entrySet()) {
            NBTTagCompound jetpackConfig = new NBTTagCompound();
            e.getValue().writeToNBT(jetpackConfig);
            toSend.setTag("Jetpack" + e.getKey(), jetpackConfig);
        }
        for (Entry<Integer, FluxPackConfig> e : Config.fluxPackConfigs.entrySet()) {
            NBTTagCompound fluxPackConfig = new NBTTagCompound();
            e.getValue().writeToNBT(fluxPackConfig);
            toSend.setTag("FluxPack" + e.getKey(), fluxPackConfig);
        }
        
        ByteBufUtils.writeTag(buf, toSend);
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.recv = ByteBufUtils.readTag(buf);
    }
    
    @Override
    public IMessage onMessage(MessageConfigSync msg, MessageContext ctx) {
        for (Entry<Integer, JetpackConfig> e : Config.jetpackConfigs.entrySet()) {
            NBTTagCompound jetpackConfig = msg.recv.getCompoundTag("Jetpack" + e.getKey());
            e.getValue().readFromNBT(jetpackConfig);
        }
        for (Entry<Integer, FluxPackConfig> e : Config.fluxPackConfigs.entrySet()) {
            NBTTagCompound fluxPackConfig = msg.recv.getCompoundTag("FluxPack" + e.getKey());
            e.getValue().readFromNBT(fluxPackConfig);
        }
        
        Jetpack.reconstructJetpacks();
        SimplyJetpacks.logger.info("Received server configuration");
        return null;
    }
    
}
