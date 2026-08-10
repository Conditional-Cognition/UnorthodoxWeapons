package com.cogworks.unorthodoxweapons.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class AdminGunShootFlashParticle extends TextureSheetParticle {

    private final float initialSize;

    protected AdminGunShootFlashParticle(ClientLevel level, double x, double y, double z,
                                          double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z, xd, yd, zd);
        this.lifetime = 20;
        this.initialSize = 0.6f;
        this.quadSize = initialSize;
        this.gravity = 0.0f;
        this.setSpriteFromAge(sprites);
        this.friction = 0.98f;
    }

    @Override
    public void tick() {
        super.tick();
        float progress = (float) age / (float) lifetime;
        this.quadSize = initialSize * (1.0f - progress);
        if (this.quadSize <= 0.01f) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        public Provider(SpriteSet sprites) { this.sprites = sprites; }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                        double x, double y, double z,
                                        double xd, double yd, double zd) {
            return new AdminGunShootFlashParticle(level, x, y, z, xd, yd, zd, sprites);
        }
    }
}