package com.cogworks.unorthodoxweapons.items.admin;

public enum AdminGunMode {
    KILL_KICK,
    BLOCK_GRAB;

    public AdminGunMode next() {
        AdminGunMode[] values = values();
        return values[(this.ordinal() + 1) % values.length];
    }
}