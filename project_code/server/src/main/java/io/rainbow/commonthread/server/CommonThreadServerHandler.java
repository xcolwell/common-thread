
package io.rainbow.commonthread.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Device;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Moment;
import io.rainbow.commonthread.protocolbuffers.MessageProtos.Sync;
import io.rainbow.commonthread.server.model.CommonThread;
import io.rainbow.commonthread.server.model.DeviceModel;

import java.util.Map;
import java.util.concurrent.Executor;

import com.google.protobuf.ByteString;



// FIXME encoder and decoder for protocol buffer messages
// FIXME use a header of type+length
public class CommonThreadServerHandler extends ChannelInboundHandlerAdapter {
    
    private Model model;
    private Map<ByteString, ChannelHandlerContext> deviceContexts;
    
    
    // FIXME serial executor to update commonThread
    // FIXME polling to remove dead devices
    
    
    

    // on moment:
    // - broadcast immediately to all other clients with data
    // - insert into data structure
    // - changes broadcast only "sequence" messages

    // on device:
    // - broadcast immediately to all other clients
    // - insert into data structure
    // - changes broadcast only "sequence" messages

    // on server -> client initial sync:
    // - broadcast only ids

    // on client -> server sync:
    // - register device with connection; the device can be timed out
    // - broadcast back to sender moment, device, momentsequence, and
    // devicesequence messages
    // for each in the data structure
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // FIXME
        // FIXME updateAndDistributeDiff
        if (msg instanceof Device) {
            // FIXME
            DeviceModel device = null; 
            model.update(commonThread ->
                commonThread.updateDevice(device));
            // FIXME store the context in deviceContexts
        } else if (msg instanceof Moment) {
            // FIXME
        } else if (msg instanceof Sync) {
            // FIXME
        } else {
            // forward
            super.channelRead(ctx, msg);
        }
    }

    
    
    private static final class Model {
        Executor modelUpdateExecutor;
        private CommonThread commonThread;
        
        void update(final ModelUpdate update) {
            modelUpdateExecutor.execute(() ->
                    update.run(commonThread));
        }
        
        static interface ModelUpdate {
            void run(CommonThread commonThread);
        }
    }
}
