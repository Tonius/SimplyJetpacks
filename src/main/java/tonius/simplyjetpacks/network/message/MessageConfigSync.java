package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.SJConfig;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {
    
    public double leadstoneSpeedVertical;
    public double leadstoneAccelVertical;
    public double leadstoneSpeedVerticalHover;
    public double leadstoneSpeedVerticalHoverSlow;
    public double leadstoneSpeedSideways;
    
    public double hardenedSpeedVertical;
    public double hardenedAccelVertical;
    public double hardenedSpeedVerticalHover;
    public double hardenedSpeedVerticalHoverSlow;
    public double hardenedSpeedSideways;
    
    public double reinforcedSpeedVertical;
    public double reinforcedAccelVertical;
    public double reinforcedSpeedVerticalHover;
    public double reinforcedSpeedVerticalHoverSlow;
    public double reinforcedSpeedSideways;
    
    public double resonantSpeedVertical;
    public double resonantAccelVertical;
    public double resonantSpeedVerticalHover;
    public double resonantSpeedVerticalHoverSlow;
    public double resonantSpeedSideways;
    
    public double fluxPlateSpeedVertical;
    public double fluxPlateAccelVertical;
    public double fluxPlateSpeedVerticalHover;
    public double fluxPlateSpeedVerticalHoverSlow;
    public double fluxPlateSpeedSideways;
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.leadstoneSpeedVertical = buf.readDouble();
        this.leadstoneAccelVertical = buf.readDouble();
        this.leadstoneSpeedVerticalHover = buf.readDouble();
        this.leadstoneSpeedVerticalHoverSlow = buf.readDouble();
        this.leadstoneSpeedSideways = buf.readDouble();
        
        this.hardenedSpeedVertical = buf.readDouble();
        this.hardenedAccelVertical = buf.readDouble();
        this.hardenedSpeedVerticalHover = buf.readDouble();
        this.hardenedSpeedVerticalHoverSlow = buf.readDouble();
        this.hardenedSpeedSideways = buf.readDouble();
        
        this.reinforcedSpeedVertical = buf.readDouble();
        this.reinforcedAccelVertical = buf.readDouble();
        this.reinforcedSpeedVerticalHover = buf.readDouble();
        this.reinforcedSpeedVerticalHoverSlow = buf.readDouble();
        this.reinforcedSpeedSideways = buf.readDouble();
        
        this.resonantSpeedVertical = buf.readDouble();
        this.resonantAccelVertical = buf.readDouble();
        this.resonantSpeedVerticalHover = buf.readDouble();
        this.resonantSpeedVerticalHoverSlow = buf.readDouble();
        this.resonantSpeedSideways = buf.readDouble();
        
        this.fluxPlateSpeedVertical = buf.readDouble();
        this.fluxPlateAccelVertical = buf.readDouble();
        this.fluxPlateSpeedVerticalHover = buf.readDouble();
        this.fluxPlateSpeedVerticalHoverSlow = buf.readDouble();
        this.fluxPlateSpeedSideways = buf.readDouble();
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(SJConfig.leadstoneSpeedVertical);
        buf.writeDouble(SJConfig.leadstoneAccelVertical);
        buf.writeDouble(SJConfig.leadstoneSpeedVerticalHover);
        buf.writeDouble(SJConfig.leadstoneSpeedVerticalHoverSlow);
        buf.writeDouble(SJConfig.leadstoneSpeedSideways);
        
        buf.writeDouble(SJConfig.hardenedSpeedVertical);
        buf.writeDouble(SJConfig.hardenedAccelVertical);
        buf.writeDouble(SJConfig.hardenedSpeedVerticalHover);
        buf.writeDouble(SJConfig.hardenedSpeedVerticalHoverSlow);
        buf.writeDouble(SJConfig.hardenedSpeedSideways);
        
        buf.writeDouble(SJConfig.reinforcedSpeedVertical);
        buf.writeDouble(SJConfig.reinforcedAccelVertical);
        buf.writeDouble(SJConfig.reinforcedSpeedVerticalHover);
        buf.writeDouble(SJConfig.reinforcedSpeedVerticalHoverSlow);
        buf.writeDouble(SJConfig.reinforcedSpeedSideways);
        
        buf.writeDouble(SJConfig.resonantSpeedVertical);
        buf.writeDouble(SJConfig.resonantAccelVertical);
        buf.writeDouble(SJConfig.resonantSpeedVerticalHover);
        buf.writeDouble(SJConfig.resonantSpeedVerticalHoverSlow);
        buf.writeDouble(SJConfig.resonantSpeedSideways);
        
        buf.writeDouble(SJConfig.fluxPlateSpeedVertical);
        buf.writeDouble(SJConfig.fluxPlateAccelVertical);
        buf.writeDouble(SJConfig.fluxPlateSpeedVerticalHover);
        buf.writeDouble(SJConfig.fluxPlateSpeedVerticalHoverSlow);
        buf.writeDouble(SJConfig.fluxPlateSpeedSideways);
    }
    
    @Override
    public IMessage onMessage(MessageConfigSync msg, MessageContext ctx) {
        SJConfig.leadstoneSpeedVertical = msg.leadstoneSpeedVertical;
        SJConfig.leadstoneAccelVertical = msg.leadstoneAccelVertical;
        SJConfig.leadstoneSpeedVerticalHover = msg.leadstoneSpeedVerticalHover;
        SJConfig.leadstoneSpeedVerticalHoverSlow = msg.leadstoneSpeedVerticalHoverSlow;
        SJConfig.leadstoneSpeedSideways = msg.leadstoneSpeedSideways;
        
        SJConfig.hardenedSpeedVertical = msg.hardenedSpeedVertical;
        SJConfig.hardenedAccelVertical = msg.hardenedAccelVertical;
        SJConfig.hardenedSpeedVerticalHover = msg.hardenedSpeedVerticalHover;
        SJConfig.hardenedSpeedVerticalHoverSlow = msg.hardenedSpeedVerticalHoverSlow;
        SJConfig.hardenedSpeedSideways = msg.hardenedSpeedSideways;
        
        SJConfig.reinforcedSpeedVertical = msg.reinforcedSpeedVertical;
        SJConfig.reinforcedAccelVertical = msg.reinforcedAccelVertical;
        SJConfig.reinforcedSpeedVerticalHover = msg.reinforcedSpeedVerticalHover;
        SJConfig.reinforcedSpeedVerticalHoverSlow = msg.reinforcedSpeedVerticalHoverSlow;
        SJConfig.reinforcedSpeedSideways = msg.reinforcedSpeedSideways;
        
        SJConfig.resonantSpeedVertical = msg.resonantSpeedVertical;
        SJConfig.resonantAccelVertical = msg.resonantAccelVertical;
        SJConfig.resonantSpeedVerticalHover = msg.resonantSpeedVerticalHover;
        SJConfig.resonantSpeedVerticalHoverSlow = msg.resonantSpeedVerticalHoverSlow;
        SJConfig.resonantSpeedSideways = msg.resonantSpeedSideways;
        
        SJConfig.fluxPlateSpeedVertical = msg.fluxPlateSpeedVertical;
        SJConfig.fluxPlateAccelVertical = msg.fluxPlateAccelVertical;
        SJConfig.fluxPlateSpeedVerticalHover = msg.fluxPlateSpeedVerticalHover;
        SJConfig.fluxPlateSpeedVerticalHoverSlow = msg.fluxPlateSpeedVerticalHoverSlow;
        SJConfig.fluxPlateSpeedSideways = msg.fluxPlateSpeedSideways;
        
        Jetpack.reconstructJetpacks();
        SimplyJetpacks.logger.info("Received server configuration");
        return null;
    }
    
}
