package net.remaster147.remastercore.mixin.impl;

import net.minecraft.client.Minecraft;
import net.remaster147.remastercore.event.EventManager;
import net.remaster147.remastercore.event.core.MinecraftPostEvent;
import net.remaster147.remastercore.event.core.MinecraftPreEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "initializeGame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;createContext()V", shift = At.Shift.AFTER))
    public void onInitializePre(CallbackInfo ci) {
        EventManager.INSTANCE.call(new MinecraftPreEvent());
    }

    @Inject(method = "initializeGame", at = @At("TAIL"))
    public void onInitializePost(CallbackInfo ci) {
        EventManager.INSTANCE.call(new MinecraftPostEvent());
    }
}
