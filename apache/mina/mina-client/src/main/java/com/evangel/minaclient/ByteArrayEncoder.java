package com.evangel.minaclient;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器
 */
public class ByteArrayEncoder extends ProtocolEncoderAdapter {
	private final Charset charset;

	public ByteArrayEncoder(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 直接将数据发出去,数据格式，包头+消息长度（int）+消息内容（json字符串）+包尾 包头包尾是十六进制字符串00 aa bb
	 * cc,转化成字节数组0, -86, -69, -52四个字节
	 *
	 * @param session
	 * @param message
	 * @param out
	 * @throws Exception
	 */
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		// 仿项目，解决断包，粘包问题
		String value = (message == null ? "" : message.toString());// 消息值
		byte[] content = value.getBytes(charset);// 消息内容,字节数组
		IoBuffer buf = IoBuffer.allocate(38 + content.length).setAutoExpand(
				true);// 缓冲区容量大小38字节加上字符长度
		buf.put(new byte[] { 0, -86, -69, -52 });// 输入包开头固定值十六进制00 aa bb
													// cc,转化成字节数组
		buf.putUnsignedInt(content.length);// int为4字节，一个字节等于2个16进制字符，所以有八位 00 00
											// 00 0c，内容长度。
		buf.put(content);// 消息内容
		buf.put(new byte[] { 0, -86, -69, -52 });// 包尾
		buf.flip();
		out.write(buf);// 写入
	}
}
