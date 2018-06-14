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


import net.reflxction.antitoxicity.AntiToxicity;

public class ConfigManager {

    private AntiToxicity m = new AntiToxicity();

    public void setEnabled(boolean b) {
        m.enabled = b;
        m.getConfig().get("All", "Enabled", true).set(b);
        m.getConfig().save();
    }

    public boolean isEnabled() {
        return m.isEnabled();
    }

}
