
package io.rainbow.commonthread.server;

import io.netty.channel.ChannelInboundHandlerAdapter;


// FIXME encoder and decoder for protocol buffer messages
// FIXME use a header of type+length
public class CommonThreadServerHandler extends ChannelInboundHandlerAdapter {

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

}
