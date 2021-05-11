package com.zoo.netty.demo.config;


import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SocketHandler extends SimpleChannelInboundHandler<String>{

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("channel active : " + ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("channel inactive : " + ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.info("exception caught: " + ctx.channel() + " \nerror message " + cause.getMessage());
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info("msg : " + msg);
	}
}