package com.pugz.bambooblocks.common.config;

import net.minecraft.client.gui.screen.CustomizeSkinScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends Screen {
    private final Screen lastScreen;

    public ConfigScreen(Screen last) {
        super(new TranslationTextComponent("config.title", new Object[0]));
        lastScreen = last;
    }

    protected void init() {
        addButton(new Button(width / 2 - 155, height / 6 + 48 - 6, 150, 20, I18n.format("config.bambooTorches", new Object[0]), (p_213055_1_) -> {
            minecraft.displayGuiScreen(new CustomizeSkinScreen(this));
        }));
    }
}