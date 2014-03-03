
package io.rainbow.commonthread.server.model;

import com.google.protobuf.ByteString;

public final class Moment {
    public ByteString id;
    public long creationTime;
    public ByteString data;

    public double sequence;
    public boolean deleted = false;
}
