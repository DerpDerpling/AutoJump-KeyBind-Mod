package name.owo.dearfox.autojumpkeybindmod.client;

import net.minecraft.text.KeybindText;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class AutoJumpKeyBindModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding auto_jump_key_bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_on = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.on", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_off = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.off", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (auto_jump_key_bind.wasPressed()) {
                MinecraftClient.getInstance().options.autoJump = !MinecraftClient.getInstance().options.autoJump;
                        client.player.sendMessage((new TranslatableText((MinecraftClient.getInstance().options.autoJump)?"autojumpkeybindmod.autojump.toggled.on":"autojumpkeybindmod.autojump.toggled.off")) , true);
                    }
                    ;
                    while (auto_jump_key_bind_on.wasPressed()) {
                        MinecraftClient.getInstance().options.autoJump = true;
                        client.player.sendMessage(new TranslatableText("option.auto-jump-key-bind.key.on"), true);
                    }
                    ;
                    while (auto_jump_key_bind_off.wasPressed()) {
                        MinecraftClient.getInstance().options.autoJump = false;
                        client.player.sendMessage(new TranslatableText("option.auto-jump-key-bind.key.off"), true);
                    }
                });
            }
        }