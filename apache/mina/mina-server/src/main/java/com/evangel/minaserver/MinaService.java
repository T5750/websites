package com.evangel.minaserver;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * mina的Service端
 */
public class MinaService {
	/** Choose your favorite port number. */
	private static final int PORT = 20000;

	public static void main(String[] args) {
		// 创建一个非阻塞的server端的Socket
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 添加日志过滤器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new ByteArrayCodecFactory(Charset
						.forName("UTF-8"))));// 自定义解编码器
		// 设置Handler
		acceptor.setHandler(new DemoServerHandler());
		// 设置读取数据的缓存区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// 读写通道10秒内无操作进入空闲状态
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("启动服务");
	}

	/**
	 * @Description: 负责session对象的创建和监听以及消息的创建和接收监听
	 */
	private static class DemoServerHandler extends IoHandlerAdapter {
		/** 服务器与客户端创建连接 */
		@Override
		public void sessionCreated(IoSession session) throws Exception {
			System.out.println("服务器与客户端创建连接...");
			super.sessionCreated(session);
		}

		@Override
		public void sessionOpened(IoSession session) throws Exception {
			System.out.println("服务器与客户端连接打开...");
			super.sessionOpened(session);
		}

		/** 消息的接收处理 */
		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			super.messageReceived(session, message);// 消息的接受
			// 传递自定义解编码器传递数组和解析数组丢包断包的
			String a = (String) message;
			System.out.println("接收到的数据：" + a);
			session.write(a);
		}

		/** 消息发送后调用 */
		@Override
		public void messageSent(IoSession session, Object message)
				throws Exception {
			super.messageSent(session, message);
			System.out.println("服务器发送消息成功...");
		}

		/** session关闭 */
		@Override
		public void sessionClosed(IoSession session) throws Exception {
			super.sessionClosed(session);
			System.out.println("断开连接：");
		}
	}
}
