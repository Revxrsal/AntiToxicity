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
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which renders text
 */
public class RenderListener extends Gui {

    private int i = 0;
    private Color c = randomColor();
    private boolean regenerateText = true;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            if (KeyListener.startRendering()) {
                if (regenerateText) {
                    Random random = new Random();
                    i = random.nextInt(wittyComments.length - 1);
                    c = randomColor();
                    regenerateText = false;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            regenerateText = true;
                        }
                    }, 4000);
                }
                drawAlert(wittyComments[i], c);

            }
        }
    }

    /**
     * Method to draw the alert using a random color
     */
    private void drawAlert(String comment, Color c) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
        drawCenteredString(renderer, comment, resolution.getScaledWidth() / 2, resolution.getScaledHeight() / 2, c.getRGB());
    }


    /**
     * An array with witty comments, for reproaching the player
     */
    private String[] wittyComments = new String[]{
            "Relax, nerd!",
            "You sure you need to use that word?",
            "Would be much better if you hadn't wrote this...",
            "Calm yourself, it's a block game!",
            "You should take a break.",
            "Touch some grass.",
            "Thats what she said".
            "Wouldn't it be a better idea not to say anything?",
            "No u"
    };


    /**
     * Generates a random color
     *
     * @return A random color
     */
    private Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

}
