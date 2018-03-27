package com.yiibai.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务器端通道
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		// 以静默方式丢弃接收的数据
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) { // (1)
				System.out.print((char) in.readByte());
				System.out.flush();
			}
		} finally {
			// ReferenceCountUtil.release(msg); // (2)
			((ByteBuf) msg).release(); // (3)
		}
		// 或者直接打印
		System.out.println("Yes, A new client in = " + ctx.name());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// 出现异常时关闭连接。
		cause.printStackTrace();
		ctx.close();
	}
}