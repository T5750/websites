package com.yiibai.netty.timepojo;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeDecoder extends ByteToMessageDecoder { // (1)
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) {
		if (in.readableBytes() < 4) {
			return;
		}
		out.add(new UnixTime(in.readUnsignedInt()));
	}
}