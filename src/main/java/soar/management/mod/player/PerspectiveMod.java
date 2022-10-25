package soar.management.mod.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import org.lwjgl.opengl.Display;
import soar.Soar;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventUpdate;
import soar.management.keybind.KeyBindManager;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;
import soar.management.setting.Setting;
import soar.mixin.mixins.client.IMixinMinecraft;
import soar.mixin.mixins.client.renderer.IMixinEntityRenderer;

// Don't know what category should i make it
public class PerspectiveMod extends Mod {
    private Setting pressToToggle;
    private float smoothCamPartialTicks;
    private float smoothCamYaw, smoothCamPitch;
    private float cameraYaw, cameraPitch;
    public boolean perspectiveToggled;
    private int prevView;
    private Setting view;

    public PerspectiveMod() {
        super("Perspective", ModCategory.PLAYER);
    }

    @Override
    public void setup() {
        this.pressToToggle = this.addBooleanSetting("Press to toggle", this, false);
        this.view = this.addSliderSetting("Initial Third Person View", this, 1, 0, 2, true);
    }

    public float getCameraYaw() {
        return perspectiveToggled ? cameraYaw : Minecraft.getMinecraft().thePlayer.rotationYaw;
    }

    public float getCameraPitch() {
        return perspectiveToggled ? cameraPitch : Minecraft.getMinecraft().thePlayer.rotationPitch;
    }

    @EventTarget
    public void onPlayerUpdate(EventUpdate event) {
        if (pressToToggle.getValBoolean()) {
            if (Soar.INSTANCE.keyBindManager.PERSPECTIVE_KEYBIND.isPressed()) {
                if (!perspectiveToggled) {
                    this.cameraYaw = mc.thePlayer.rotationYaw;
                    this.cameraPitch = mc.thePlayer.rotationPitch;
                    prevView = mc.gameSettings.thirdPersonView;
                    mc.gameSettings.thirdPersonView = view.getValInt();
                    perspectiveToggled = true;
                } else {
                    mc.gameSettings.thirdPersonView = prevView;
                    perspectiveToggled = false;
                }
            }
        } else {
            if (Soar.INSTANCE.keyBindManager.PERSPECTIVE_KEYBIND.isKeyDown()) {
                if (!perspectiveToggled) {
                    this.cameraYaw = mc.thePlayer.rotationYaw;
                    this.cameraPitch = mc.thePlayer.rotationPitch;
                    prevView = mc.gameSettings.thirdPersonView;
                    mc.gameSettings.thirdPersonView = view.getValInt();
                    perspectiveToggled = true;
                }
            } else {
                if (perspectiveToggled) {
                    mc.gameSettings.thirdPersonView = prevView;
                    perspectiveToggled = false;
                }
            }
        }
    }

    public void handleCamera() {
        if (!perspectiveToggled) return;
        if (this.mc.inGameHasFocus && Display.isActive()) {
            this.mc.mouseHelper.mouseXYChange();
            float partialTicks = ((IMixinMinecraft) mc).getPartialTicks();
            float f = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
            float f1 = f * f * f * 8.0F;
            float f2 = (float) this.mc.mouseHelper.deltaX * f1;
            float f3 = (float) this.mc.mouseHelper.deltaY * f1;

            IMixinEntityRenderer entityRenderer = (IMixinEntityRenderer) mc.entityRenderer;

            float smoothCamFilterX = entityRenderer.getSmoothCamFilterX();
            float smoothCamFilterY = entityRenderer.getSmoothCamFilterY();

            int i = 1;
            if (this.mc.gameSettings.invertMouse) {
                i = -1;
            }

            if (this.mc.gameSettings.smoothCamera) {
                entityRenderer.setSmoothCamYaw(entityRenderer.getSmoothCamYaw() + f2);
                entityRenderer.setSmoothCamPitch(entityRenderer.getSmoothCamPitch() + f3);

                float partialTicksDiff = partialTicks - entityRenderer.getSmoothCamPartialTicks();
                entityRenderer.setSmoothCamPartialTicks(partialTicks);
                f2 = smoothCamFilterX * partialTicksDiff;
                f3 = smoothCamFilterY * partialTicksDiff;
            } else {
                this.smoothCamYaw = 0.0F;
                this.smoothCamPitch = 0.0F;
            }
            this.cameraYaw += f2 * 0.3;
            this.cameraPitch += (f3 * 0.3) * i;
        }
    }

}
