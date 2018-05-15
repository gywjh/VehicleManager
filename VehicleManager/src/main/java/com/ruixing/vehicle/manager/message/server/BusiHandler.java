package com.ruixing.vehicle.manager.message.server;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.ruixing.vehicle.manager.utils.JT809Constants;

public class BusiHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Message msg = (Message) e.getMessage();
		switch (msg.getMsgId()) {
		case 0x1001:
			login(msg, ctx, e);
			break;
		case 0x1005:
			System.out.println("主链路连接保持请求消息。");
			heartBeat(msg, ctx, e);
			break;
		case 0x9001:
			System.out.println("从链路连接应答消息");
		default:
			break;
		}
	}

	/**
	 * 登录验证
	 * 
	 * @param msg
	 * @param ctx
	 * @param e
	 */
	private void login(Message msg, ChannelHandlerContext ctx, MessageEvent e) {
		int checkResult = JT809Constants.UP_CONNECT_RSP_SUCCESS;
		int userId = msg.getMsgBody().readInt();
		String passWord = msg.getMsgBody().readBytes(10).toString(Charset.forName("GBK"));
		String ip = msg.getMsgBody().readBytes(32).toString(Charset.forName("GBK"));
		int port = msg.getMsgBody().readUnsignedShort();
		msg.getMsgBody().clear();
		if (userId != JT809Constants.LOGIN_USER_ID) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_03;
		} else if (!JT809Constants.LOGIN_USER_PASSWORD.equals(passWord)) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_04;
		} else if (!JT809Constants.SERVER_IP.equals(ip)) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_01;
		} else if (port != JT809Constants.SERVER_PORT) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_06;
		}
		Message msgRep = new Message(JT809Constants.UP_CONNECT_RSP);
		ChannelBuffer buffer = ChannelBuffers.buffer(5);
		buffer.writeByte(checkResult);
		// 校验码，临时写死
		buffer.writeInt(1111);
		msgRep.setMsgBody(buffer);
		// ChannelFuture f =
		e.getChannel().write(msgRep);
		// f.addListener(ChannelFutureListener.CLOSE);
	}

	private void heartBeat(Message msg, ChannelHandlerContext ctx, MessageEvent e) {
		Message msgRep = new Message(JT809Constants.UP_LINKTEST_RSP);
		ChannelBuffer buffer = ChannelBuffers.buffer(0);
		msgRep.setMsgBody(buffer);
		// ChannelFuture f =
		e.getChannel().write(msgRep);
	}

	private void collectVehicleInfo(Message msg, ChannelHandlerContext ctx, MessageEvent e)
	{
		Message msgRep = new Message(JT809Constants.UP_LINKTEST_RSP);
		ChannelBuffer buffer = ChannelBuffers.buffer(0);
		e.getChannel().write(msgRep);
	}
}