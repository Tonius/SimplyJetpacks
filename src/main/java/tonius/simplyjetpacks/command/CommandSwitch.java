package tonius.simplyjetpacks.command;

import java.util.Collections;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import tonius.simplyjetpacks.item.ItemFluxPack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.fluxpack.FluxPack;
import tonius.simplyjetpacks.item.jetpack.Jetpack;
import tonius.simplyjetpacks.item.jetpack.JetpackJetPlate;
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
                if (armor != null) {
                    if (args[0].equals("charger")) {
                        if (armor.getItem() instanceof ItemJetpack) {
                            Jetpack jetpack = ((ItemJetpack) armor.getItem()).getJetpack(armor);
                            if (jetpack != null && jetpack instanceof JetpackJetPlate) {
                                ((JetpackJetPlate) jetpack).toggleCharger(armor, (EntityPlayer) sender, true);
                                return;
                            }
                            sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noCharger")));
                            return;
                        } else if (armor.getItem() instanceof ItemFluxPack) {
                            FluxPack fluxpack = ((ItemFluxPack) armor.getItem()).getFluxPack(armor);
                            if (fluxpack != null) {
                                fluxpack.toggle(armor, (EntityPlayer) sender, true);
                                return;
                            }
                            sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noCharger")));
                            return;
                        }
                    }
                    if (args[0].equals("ehover")) {
                        if (armor.getItem() instanceof ItemJetpack) {
                            Jetpack jetpack = ((ItemJetpack) armor.getItem()).getJetpack(armor);
                            if (jetpack != null) {
                                if (!jetpack.switchEmergencyHoverMode(armor, (EntityPlayer) sender)) {
                                    sender.addChatMessage(new ChatComponentText(StringUtils.LIGHT_RED + StringUtils.translate("command.switch.noEmergencyHover")));
                                }
                                return;
                            }
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
