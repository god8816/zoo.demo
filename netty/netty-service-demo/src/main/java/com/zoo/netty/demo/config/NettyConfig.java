package com.zoo.netty.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * netty加载
 */
@Slf4j
@Configuration
public class NettyConfig {
	
	/**
     * boss线程组
     */
    EventLoopGroup bossGroup = new NioEventLoopGroup(4);
    
    /**
     * worker线程组
     */
    EventLoopGroup workGroup = new NioEventLoopGroup(16);

	/**
	 * spring boot项目注入netty且netty第一步加载
	 * */
    @Order(1)
    @Bean(initMethod="startNetty",destroyMethod="stopNetty")
    public NettyConfig initNetty() {
    	   log.info("netty开始初始化");
		return new NettyConfig();
    }
    
    public void startNetty() {
		try {
	    	    //netty入口引导类
	     	ServerBootstrap b = new ServerBootstrap();
			//设置核心线程组和业务工作线程组
			b.group(bossGroup, workGroup);
			//设置nio类型的channel
			b.channel(NioServerSocketChannel.class);
	        b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					/**最大字符支持及分隔符*/
					pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(10000, Delimiters.lineDelimiter()));
					/**编码类型*/
					pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
					pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
					/**超时控制*/
					pipeline.addLast("idleStateHandler", new IdleStateHandler(200,0,0));
					/**超时拦截*/
					pipeline.addLast("timeoutHandler", new TimeoutHandler());
					pipeline.addLast("handler",new SocketHandler());
					
			}});
	        b.bind(4201).sync();
	        //spring环境不建议在这里写下面语句会阻塞springboot主线程
	        //future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("netty异常：{}",e.getMessage()); 
			e.printStackTrace();
		} 
    }
    
    public void stopNetty() {
  	   log.info("stopNetty");
  	   bossGroup.shutdownGracefully();
	   workGroup.shutdownGracefully();
     }
}
