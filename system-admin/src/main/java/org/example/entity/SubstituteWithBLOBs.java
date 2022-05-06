package org.example.entity;

public class SubstituteWithBLOBs extends Substitute {
    private byte[] picture;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}