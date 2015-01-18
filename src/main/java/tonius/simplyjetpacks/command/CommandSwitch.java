package tonius.simplyjetpacks.command;

import java.util.Collections;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.meta.FluxPack;
import tonius.simplyjetpacks.item.meta.JetPlate;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.util.StringUtils;

public class CommandSwitch extends CommandBase {
    
    @Override
    public String getCommandName() {
        return "simplyjetpacks_switch";
    }
    
    @Override
    public List getCommandAliases() {
        return Collections.singletonList("sjs");
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/simplyjetpacks_switch <charger|ehover>";
    }
    
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            if (args.length == 1) {
                ItemStack armor = ((EntityPlayer) sender).getEquipmentInSlot(3);
                nopack: if (armor != null && armor.getItem() instanceof ItemPack) {
                    PackBase pack = ((ItemPack) armor.getItem()).getPack(armor);
                    if (pack == null) {
                        break nopack;
                    }
                    if (args[0].equals("charger")) {
                        if (pack instanceof JetPlate) {
                            ((JetPlate) pack).toggleCharger(armor, (EntityPlayer) sender, true);
                            return;
                        } else if (pack instanceof FluxPack) {
                            ((FluxPack) pack).toggleOn(armor, (EntityPlayer) sender, false, true);
                            return;
                        }
                        sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noCharger")));
                    }
                    if (args[0].equals("ehover")) {
                        if (pack instanceof Jetpack) {
                            Jetpack jetpack = (Jetpack) pack;
                            if (!jetpack.emergencyHoverMode) {
                                sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noEmergencyHover")));
                            } else {
                                jetpack.switchEHover(armor, (EntityPlayer) sender);
                            }
                            return;
                        }
                        sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noJetpack")));
                        return;
                    }
                }
                sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.neither")));
            } else {
                throw new WrongUsageException(this.getCommandUsage(sender));
            }
        }
    }
    
}
