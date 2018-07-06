package com.evangel.minaserver;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 自定义解码器，确保能读到完整的包
 */
public class ByteArrayDecoder extends CumulativeProtocolDecoder {
	private final Charset charset;

	public ByteArrayDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer,
			ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
		// 丢包，断包处理
		if (ioBuffer.remaining() > 4)// 有包头，包头足够
		{
			ioBuffer.mark();// 标记当前position的快照标记mark，以便后继的reset操作能恢复position位置，开始是0
			byte[] l = new byte[4];
			ioBuffer.get(l);// 读取包头，占4个字节
			if (ioBuffer.remaining() < 4)// 内容长度的4个字节不够，断包
			{
				ioBuffer.reset();
				return false;//
			} else {// 内容长度的4个字节数组足够
				byte[] bytesLegth = new byte[4];// 内容长度
				ioBuffer.get(bytesLegth);// 读取内容长度,int类型，占四个字节
				int len = MinaUtil.byteArrayToInt(bytesLegth);// 内容长度有多少
				if (ioBuffer.remaining() < len)// 内容不够，断包
				{
					ioBuffer.reset();
					return false;//
				} else { // 消息内容足够
					byte[] bytes = new byte[len];
					ioBuffer.get(bytes, 0, len);
					protocolDecoderOutput.write(new String(bytes, charset));// 读取内容，并且发送
					if (ioBuffer.remaining() < 4) {// 包尾不够
						ioBuffer.reset();
						return false;//
					} else {// 包尾足够
						byte[] tails = new byte[4];
						ioBuffer.get(tails);// 读取包尾
						if (ioBuffer.remaining() > 0)// 最后如果粘了包，会再次调用doDeocde()方法，把剩余数据给doDeocde()方法处理
						{
							return true;
						}
					}
				}
			}
		}
		return false;// 断包，或者执行完，
	}
}
