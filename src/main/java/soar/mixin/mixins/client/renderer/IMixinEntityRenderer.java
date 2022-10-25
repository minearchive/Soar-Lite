package soar.mixin.mixins.client.renderer;

public interface IMixinEntityRenderer {
    float getSmoothCamFilterX();
    float getSmoothCamFilterY();

    void setSmoothCamYaw(float f);
    void setSmoothCamPitch(float f);

    float getSmoothCamYaw();
    float getSmoothCamPitch();

    void setSmoothCamPartialTicks(float f);
    float getSmoothCamPartialTicks();
}
