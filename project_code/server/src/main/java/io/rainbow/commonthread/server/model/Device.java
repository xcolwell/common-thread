
package io.rainbow.commonthread.server.model;

import javax.annotation.Nullable;

import com.google.protobuf.ByteString;

public final class Device {
    public ByteString id;
    public double[] position;
    public float[] orientation;

    public @Nullable Device anchor = null;

    public int sequence;
    /* corner[-1] controls[-1] corner[0] controls[0] corner[1] */
    public final float[] interpolation = new float[27];
    /* distance(-1, 0) distance(0, 1) */
    public float[] distances = new float[2];
}
