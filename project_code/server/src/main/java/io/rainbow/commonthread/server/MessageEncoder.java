package io.rainbow.commonthread.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Device;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.DeviceSequence;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Diff;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Header;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Moment;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.MomentSequence;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Sync;

import java.nio.ByteBuffer;
import java.util.List;

import com.google.protobuf.MessageLite;

public class MessageEncoder extends MessageToMessageEncoder<MessageLite> {

    
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, List<Object> out)
            throws Exception {
        int type;
        if (msg instanceof Device) {
            type = Header.Type.Device_VALUE;
        } else if (msg instanceof DeviceSequence) {
            type = Header.Type.DeviceSequence_VALUE;
        } else if (msg instanceof Moment) {
            type = Header.Type.Moment_VALUE;
        } else if (msg instanceof MomentSequence) {
            type = Header.Type.MomentSequence_VALUE;
        } else if (msg instanceof Sync) {
            type = Header.Type.Sync_VALUE;
        } else if (msg instanceof Diff) {
            type = Header.Type.Diff_VALUE;
        } else {
            // ignore
            type = -1;
        }
        
        if (0 < type) {
            byte[] b = msg.toByteArray();
            out.add(ByteBuffer.allocate(4 + b.length)
                    .putInt(type)
                    .put(b)
                    .array());
        }
    }
}
