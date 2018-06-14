/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.antitoxicity.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.reflxction.antitoxicity.utils.ChatColor;
import net.reflxction.antitoxicity.utils.ConfigManager;
import net.reflxction.antitoxicity.utils.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Command ("/antitoxicity") to control/configure the mod
 */
public class ATCommand implements ICommand {

    private ConfigManager manager = new ConfigManager();

    /**
     * @return Command name after the slash
     */
    @Override
    public String getCommandName() {
        return "antitoxicity";
    }

    /**
     * @return Command usage
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/antitoxicity <toggle>";
    }

    /**
     * @return List of all aliases for the command
     */
    @Override
    public List<String> getCommandAliases() {
        return new ArrayList<>();
    }

    /**
     * Command process
     *
     * @param sender Sender of the command
     * @param args   Arguments
     * @throws CommandException If any error occurs while invoking the command
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        switch (args.length) {
            case 0:
                sendMessage("&cIncorrect command usage. Try " + getCommandUsage(sender));
                break;
            case 1:
                // Toggle the mod
                if (args[0].equalsIgnoreCase("toggle")) {
                    manager.setEnabled(!manager.isEnabled());
                    sendMessage(manager.isEnabled() ? "&aAnti-Toxicity has been enabled" : "&cAnti-Toxicity has been disabled");
                } else {
                    sendMessage("&cInvalid arguments. Try " + getCommandUsage(sender));
                }
        }
    }

    /**
     * @return True if sender is allowed to use the command (generally for enabling/disabling the command)
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    /**
     * @return List of tab completions for the command
     */
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tab = new ArrayList<>();
        tab.add(getCommandName());
        return tab;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    /**
     * @param text Send a message to the client player
     */
    private void sendMessage(String text) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.translateAlternateColorCodes(Reference.PREFIX + text)));
    }

}
