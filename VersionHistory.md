1.5.0 - (unreleased)
----------------------------------
- Now depends on CoFHLib. Using CoFH Core for this is recommended. CoFHLib also works, but it has to be build 152+ (from CurseForge).
- Added support for BuildCraft 6! SJ now provides two kinds of BuildCraft-based jetpacks, based on which BC modules are installed. One is powered by Fuel, and one is powered by RF. Will hopefully also work with BuildCraft 7, though this is largely untested.
- Added Jetpack Fueller item for filling jetpacks with Fluids from Tanks or similar.
- Made TE-based Thrusters slightly cheaper and less annoying to craft.
- Only one kind of mod integration is now enabled by default: always TE if it is present, or always EIO if TE is not present, or lastly BC if neither of the previous are present.
- Split 'secondary' (sneak-activated) keybinds into keybinds of their own.
- Added a GUI for worn jetpacks and flux packs, opened by pressing a key (U by default). It shows their current fuel levels, and lets you turn various features on and off. This allows you to unmap some keybinds that do the same things.
- Updated default energy-related config options to reflect recent changes in TE's default config options.
- Using a jetpack underwater is now a bit slower, and makes it produce bubbles.
- Added a config option to make jetpacks explode and kill their user when flying through flammable fluids. Blame Vexatos for this idea. Disabled by default.
- Readded the option to disable 3D armor models. This is for people that experience issues with the armor models and mods like Smart Moving.
- Fixed not being able to turn off only part of the HUD.

1.4.1 - 2015-02-20
----------------------------------
- Fixed crash on dedicated server startup

1.4.0 - 2015-02-20
----------------------------------
- Added jetpack sounds. Many thanks to nabijaczleweli for their help with this one. The volume for these sounds can be set with the volume slider for the 'Players' sound category. They can also be disabled as a whole in the config (clientside).
- Refactored code to be cleaner, less redundant and more flexible.
- All jetpack/flux pack types can now be tuned individually (instead of being bound to a list of tiers).
- The Flux-Infused JetPlate is now craftable even if Redstone Arsenal is not installed.
- Localized keybind names. The mod's keybinds will likely be reset when updating to this version.
- Jetpack particles now spread out a little bit instead of going straight down.
- Creative Jetpacks now have rainbow smoke particles by default.
- Added a small limit on how quickly jetpacks can be charged by external sources. (Configurable)
- Removed 'flat' jetpack armor models.
- Texture pack authors, you'll have to rename some texture files: tuberous/creative/icon jetpacks, creative flux packs, and 3d jetpack armor models.


1.3.2 - 2014-12-27
----------------------------------
- Armor is now properly configurable by reduction amounts
- Fixed major bug in armor that could negate armor values
- Fixed energy HUD scale affecting other mod HUDs


1.3.1 - 2014-12-16
----------------------------------
- Updated Capacitor Pack recipes for the new version of Ender IO


1.3.0 - 2014-12-06
----------------------------------
- Added 3D armor models for the jetpacks. Many thanks to Cybercat and dand0 for providing a model. (There is a config option to switch back to the boring old flat jetpacks.)
- Added a configurable speed boost when sprinting while flying forward
- Adding or removing armor is now done using a crafting grid. This allows armored jetpacks to be auto-crafted. Note that removing armor will only properly return plating if done by a player.
- Removed armoring NEI handlers as they are no longer needed
- Emergency hover mode can be toggled by switching hover mode while sneaking
- Added /simplyjetpacks_switch (/sjs) command to switch emergency hover and chargers, and a config option to disable secondary actions when sneaking and pressing the toggling keys
- Sneaking while flying slows down sideways flight to half speed
- Jetpacks and Flux Packs are now on by default upon creation
- Sneak-right clicking a jetpack on a Zombie or a Skeleton will force it to wear and use it
- Fixed armored jetpacks absorbing much more damage than intended. This was making players invincible when they were also wearing other pieces of armor
- Fixed engine/hover controls not working when bound to mouse buttons
- Updated CoFH Energy API, and actually include the whole package this time *cough*
- RIP, resource pack authors


1.2.0 - 2014-10-18
----------------------------------
- Added full EnderIO support! This includes EnderIO versions of the jetpacks, flux packs and components.
- Integration with TE or EIO can be disabled/enabled in the config
- Changed the structure of the config... again. Mainly the tuning portions of it, make sure to transfer over any edits you did to them if needed.
- HUD now hides if F3 debug mode is enabled
- Code refactors and cleanups
- Note: the item IDs of Creative Jetpack/Flux Packs, Tuberous Jetpacks and Armor Platings have changed. Shouldn't cause any problems, they will just disappear or change into missing textures which you can just delete.


1.1.4 - 2014-10-02
----------------------------------
- Removed potentially infinite loop when upgrading jetpacks
- Fixed Leadstone Jetpack recipe not taking power from used Leadstone Flux Capacitors
- Recipes that use glass blocks now use Ore Dictionary glass


1.1.3 - 2014-09-25
----------------------------------
- Fixed the Flux-Infused JetPlate not being craftable with a (partially) charged Flux-Infused Chestplate


1.1.2 - 2014-09-24
----------------------------------
- Fixed spamming isue in the client console


1.1.1 - 2014-09-14
----------------------------------
- Added the Mysterious Potato (creative-only). It can do the thing. The thing involves spawners.
- Mobs can use jetpacks again. Should be less crashy than last time.
- Enchantability can now be set per jetpack/flux pack
- Jetpack particles now respect the particles option in the video settings menu
- Split the config file so that the client-only options are kept separate
- Fly and descend keys can now be changed using the configs, to be something else than the vanilla jump and sneak keys.
- Fixed potential memory leak in single player
- Various code cleanups


1.1.0 - 2014-08-16
----------------------------------
- Added Flux Packs. These items are worn as chestplates and can charge held items and other worn armor.
- Added the Flux-Infused JetPlate. Only available when Redstone Arsenal is installed, this is the new final tier of jetpacks. It is basically the Flux-Infused Chestplate, the Resonant Jetpack and the Resonant Flux Pack in one.
- Hover mode no longer makes the jetpack use less power. Default tuning values have been adjusted to balance this out.
- Some rebalancing of the default tuning values. If you wish to use these, use the config GUI to reset the tuning values, or delete the config file.
- Added a NEI plugin to show the 'recipes' for armored jetpacks.
- Replaced the status chat messages with a new part in the HUD that displays the states of the jetpack engine, hover mode, etc. Chat messages can still be enabled in the config.
- Updated CoFH API


1.0.1 - 2014-08-02
----------------------------------
- Fixed a bug in jetpack upgrading recipes


1.0.0 - 2014-07-30
----------------------------------
- Ported to Minecraft 1.7.10 and Thermal Expansion 4
- NOT compatible with any previous versions. Pretty much everything changed internally.
- Added support for the new ingame config GUI system
- Jetpacks are now meta items. All jetpacks are under the same (internal) item id.
- Made particles a lot smoother on laggy servers
- Jetpacks now save everything when upgraded, including on/off state, display names and enchantments
- Added safety features. Higher tier jetpacks will save their users from falling to their death or into the void. (Configurable)
- Made jetpack enchantability configurable
- Renamed Redstone Jetpack to Reinforced Jetpack
- Slightly altered creative tab icon
- Some tweaks to the default tuning values
- Some tweaks to tooltips. Energy level is now always visible. Also added some more info to the rest of the tooltips.
- Made the energy HUD a lot more configurable
- LOTS of changes in the format of the language files. It is likely that anything other than English will break. If you want to fix this, please open a pull request.
- Also a lot of changes to what the texture file names are called. Sorry, texture pack users!


0.3.1.1 - 2014-06-30
----------------------------------
- Fixed a crash when using a Creative Jetpack and taking damage


0.3.1 - 2014-06-27
----------------------------------
- Hopefully fixed armored jetpacks sometimes breaking when on low power
- Fixed possible item dupes with Tinkers' Construct
- Armored jetpacks use power when getting hit (power usage is configurable)
- Displaying stored energy no longer changes a jetpack item's damage value. Don't worry, the damage bar still works fine.
- Fixed a crash when attempting to 'remove' armor from a creative jetpack


0.3.0.0 - 2014-05-29
----------------------------------
- Added Creative Jetpack. Not Creative as in creative-flight, but as in infinite charge, like the Creative TE items.
- Added Particle Customizers. They can be used to disable or change the type of particles on specific jetpacks.
- Jetpack particles from other players can now actually be seen from a distance
- Jetpacks now also boost sideways and backwards when flying around (it used to only boost forward)
- Tuberous jetpacks can now be tuned in the configs
- Added config to disable the "Hold Shift for details"
- Improved armored jetpack item textures
- Hopefully fixed a crash with Morph interaction
- I would call this 0.2.1.0, but it would probably get confused with 0.2.0.1. :V


0.2.0.1 - 2014-05-24
----------------------------------
- Fixed a small yet annoying bug with the energy HUD


0.2 - 2014-05-24
----------------------------------
- Added a way to put armor on jetpacks. Refer to the jetpacks' tooltips for more info
- Added a basic ingame jetpack energy HUD. The HUD's position cannot be changed yet, if this bothers you the HUD can be disabled in the config
- Small rebalances in thruster and jetpack recipes
- Jetpacks no longer require a chestplate to be crafted
- Jetpacks can now be upgraded from any tier to any tier, if the config is not set to force going through the tiers
- Thrusters can now be used to do high jumps
- Improved item tooltips
- Added a config option to invert the hover mode sneaking behavior
- Added a config option to hide the death warning in Tuberous Jetpacks tooltips
- Added Russian translation (Thanks to Adaptivity)
- Various cleanups in the code


0.1.4.1 - 2014-05-03
----------------------------------
- Attempt to fix crashes with jetpack upgrading recipes in mod workbenches


0.1.4 - 2014-05-02
----------------------------------
- Mobs are now able to 'use' jetpacks when picking them up
- Mobs wearing jetpacks will die when flying past the world height limit
- Added a config option to only allow crafting jetpacks by upgrading previous tiers
- Tuberous jetpacks are now even more cartoonish
- Added a config option to disable tuberous jetpack crafting
- Jetpacks now retain their stored power when upgraded
- Jetpacks will take the stored power from flux capacitors in crafting
- Added localization support
