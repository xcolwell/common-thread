
package io.rainbow.commonthread.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public final class CommonThreadServer {
    /** 43690, rAAAAinbow */
    static final int DEFAULT_PORT = 0xaaaa;

    private final int port;

    public CommonThreadServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // FIXME log
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            
                            // Decoders
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(/* 1mib max frame length */ 1024 * 1024, 
                                    0, 4, 0, 4));
                            pipeline.addLast(new MessageDecoder());
                            
                            // Encoder
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast(new MessageEncoder());
                            
                            // Handlers
                            pipeline.addLast(new CommonThreadServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            
            ChannelFuture f = b.bind(port).sync();
            
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (0 < args.length) {
            port = Integer.parseInt(args[0]);
        } else {
            port = DEFAULT_PORT;
        }
        new CommonThreadServer(port).run();
    }
}
