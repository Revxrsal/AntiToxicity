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

package net.reflxction.antitoxicity.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.reflxction.antitoxicity.AntiToxicity;
import net.reflxction.antitoxicity.utils.ReflectUtils;
import net.reflxction.antitoxicity.utils.WordsList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which listens to stuff written in the chat text field
 */
public class KeyListener extends WordsList {

    private static boolean render = false;

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (AntiToxicity.isEnabled()) {
            // Checking if the chat gui is opened
            if (FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
                // Get the text field
                GuiTextField field = ReflectUtils.getMainTextField();
                if (check(field)) {
                    Minecraft.getMinecraft().displayGuiScreen(null);
                    render = true;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            render = false;
                        }
                    }, 3500);
                }
            }
        }
    }

    static boolean startRendering() {
        return render;
    }

    private boolean check(GuiTextField field) {
        return
                // Field is valid
                field != null &&
                        // Player has written in text it
                        !field.getText().isEmpty() &&
                        // The current text contains any word from the words list
                        getWords().stream().anyMatch(field.getText().toLowerCase()::contains);
    }


}
