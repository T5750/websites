package com.evangel.minaclient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient extends JFrame {
	/**
	 * 线程池，避免阻塞主线程，与服务器建立连接使用，创建一个只有单线程的线程池，尽快执行线程的线程池
	 */
	private static ExecutorService executorService = Executors
			.newSingleThreadExecutor();
	/**
	 * 连接对象
	 */
	private NioSocketConnector mConnection;
	/**
	 * session对象
	 */
	private IoSession mSession;
	/**
	 * 连接服务器的地址
	 */
	private InetSocketAddress mAddress;
	private ConnectFuture mConnectFuture;
	private static final long serialVersionUID = 1538675161745436968L;
	private JButton sendButton;
	private JTextArea area;
	private JScrollBar scroll;

	public MinaClient() {
		super("Chat Client based on Apache MINA");
		initConfig();
		connect();
		sendButton = new JButton(new MinaClient.SendAction());
		sendButton.setText("Send");
		area = new JTextArea(20, 50);
		area.setLineWrap(true);
		area.setEditable(false);
		scroll = new JScrollBar();
		scroll.add(area);
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
		left.add(area);
		left.add(Box.createRigidArea(new Dimension(0, 5)));
		left.add(Box.createHorizontalGlue());
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		right.add(Box.createRigidArea(new Dimension(0, 25)));
		right.add(sendButton);
		p.add(left);
		p.add(Box.createRigidArea(new Dimension(10, 0)));
		p.add(right);
		getContentPane().add(p);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class SendAction extends AbstractAction {
		private static final long serialVersionUID = -6389802816912005370L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (mConnectFuture != null && mConnectFuture.isConnected()) {// 与服务器连接上
				long id = System.currentTimeMillis();
				mConnectFuture.getSession().write(
						"{\"id\":" + id + ",\"name\":\"name-" + id + "\"}");// 发送json字符串
			}
		}
	}

	public static void main(String[] args) {
		MinaClient client = new MinaClient();
		client.pack();
		client.setVisible(true);
	}

	/**
	 * 初始化Mina配置信息
	 */
	private void initConfig() {
		mAddress = new InetSocketAddress("127.0.0.1", 20000);// 连接地址,此数据可改成自己要连接的IP和端口号
		mConnection = new NioSocketConnector();// 创建连接
		// 设置读取数据的缓存区大小
		SocketSessionConfig socketSessionConfig = mConnection
				.getSessionConfig();
		socketSessionConfig.setReadBufferSize(2048);
		socketSessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 4);// 设置4秒没有读写操作进入空闲状态
		mConnection.getFilterChain().addLast("logging", new LoggingFilter());// logging过滤器
		mConnection.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new ByteArrayCodecFactory(Charset
						.forName("UTF-8"))));// 自定义解编码器
		mConnection.setHandler(new DefaultHandler());// 设置handler
		mConnection.setDefaultRemoteAddress(mAddress);// 设置地址
	}

	/**
	 * 创建连接
	 */
	private void connect() {
		FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
			@Override
			public Void call() {
				try {
					while (true) {
						mConnectFuture = mConnection.connect();
						mConnectFuture.awaitUninterruptibly();// 一直等到他连接为止
						mSession = mConnectFuture.getSession();// 获取session对象
						if (mSession != null && mSession.isConnected()) {
							System.out.println("连接成功");
							break;
						}
						Thread.sleep(3000);// 每隔三秒循环一次
					}
				} catch (Exception e) {// 连接异常
				}
				return null;
			}
		});
		executorService.execute(futureTask);// 执行连接线程
	}

	private void append(String text) {
		area.append(text);
	}

	/**
	 * Mina处理消息的handler,从服务端返回的消息一般在这里处理
	 */
	private class DefaultHandler extends IoHandlerAdapter {
		@Override
		public void sessionOpened(IoSession session) throws Exception {
			super.sessionOpened(session);
		}

		/**
		 * 接收到服务器端消息
		 */
		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			append(message + "\n");
			System.out.println("接收到服务器端消息：" + message.toString());
		}

		@Override
		public void sessionIdle(IoSession session, IdleStatus status)
				throws Exception {// 客户端进入空闲状态.
			super.sessionIdle(session, status);
		}
	}
}
