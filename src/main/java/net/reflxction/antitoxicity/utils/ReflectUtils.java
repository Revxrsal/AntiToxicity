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
package net.reflxction.antitoxicity.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

/**
 * A small class to help access protected/private values from the {@link net.minecraft.client.gui.GuiChat} class
 */
public class ReflectUtils {

    /**
     * A method which uses reflection to get the chat text field, since it has protected access
     *
     * @return Text field
     */
    public static GuiTextField getMainTextField() {
        try {
            GuiChat chatGUI = ((GuiChat) Minecraft.getMinecraft().currentScreen);
            Class guiClass = chatGUI.getClass();
            Field fieldText = getField(guiClass, "inputField");
            fieldText.setAccessible(true);
            return (GuiTextField) ReflectionHelper.findField(GuiChat.class, "inputField", "field_146409_v").get(chatGUI);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A method which uses reflection, to return a Field with an object from that class
     *
     * @param clazz     Class to get value from
     * @param fieldName Name of the field in the class
     * @return Field with the given name
     * @throws NoSuchFieldException If the field wasn't found
     */
    private static Field getField(Class clazz, String fieldName)
            throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }
}
