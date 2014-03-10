package io.rainbow.commonthread.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Device;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.DeviceSequence;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Header;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Moment;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.MomentSequence;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Sync;

import java.util.List;

import javax.annotation.Nullable;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;


public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
            throws Exception {
        
        int type = msg.readInt();
        @Nullable Parser<? extends MessageLite> parser;
        switch (type) {
            case Header.Type.Device_VALUE:
                parser = Device.PARSER;
                break;
            case Header.Type.DeviceSequence_VALUE:
                parser = DeviceSequence.PARSER;
                break;
            case Header.Type.Moment_VALUE:
                parser = Moment.PARSER;
                break;
            case Header.Type.MomentSequence_VALUE:
                parser = MomentSequence.PARSER;
                break;
            case Header.Type.Sync_VALUE:
                parser = Sync.PARSER;
                break;
            default:
                // ignore
                parser = null;
                break;
        }
        
        int length = msg.readableBytes();
        if (null != parser) {
            byte[] b = new byte[length];
            msg.readBytes(b, 0, length);
            out.add(parser.parseFrom(b));
        } else {
            // ignore
            msg.skipBytes(length);
        }
    }
}
