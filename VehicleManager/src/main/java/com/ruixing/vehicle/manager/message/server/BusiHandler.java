package com.ruixing.vehicle.manager.message.server;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.message.service.MessageServcie;
import com.ruixing.vehicle.manager.utils.JT809Constants;

public class BusiHandler extends SimpleChannelHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MessageServcie messageServcie;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Message msg = (Message) e.getMessage();
		switch (msg.getMsgId()) {
		case 0x1001:
			login(msg, ctx, e);
			break;
		case 0x1005:
			heartBeat(msg, ctx, e);
			break;
		case 0x1200:
			// 上报车辆位置
			expMessage(msg, ctx, e);
		default:
			break;
		}
	}

	private void expMessage(Message msg, ChannelHandlerContext ctx, MessageEvent e) {
		logger.info("接收中交推送消息：" + msg.getMsgBody().toString());
		ChannelBuffer megBody = msg.getMsgBody();
		String chpNo = megBody.readBytes(21).toString(Charset.forName("GBK"));
		Byte chpColor = megBody.readByte();
		int subTask = megBody.readShort();
		int subcapacity = megBody.readInt();
		logger.info(chpColor + "------" + subTask + "==========" + subcapacity);
		System.out.println(megBody.readByte());
		System.out.println(megBody.readBytes(4));
		System.out.println(megBody.readBytes(3));
		int lon = megBody.readInt(); // 经度
		int lat = megBody.readInt(); // 纬度
		Short speed = megBody.readShort(); // 车速
		Short recordSpeed = megBody.readShort(); // 记录车速
		int mli = megBody.readInt(); // 里程
		Short direction = megBody.readShort(); // 方向
		logger.info("经度：- " + lon);
		logger.info("纬度：- " + lat);
		// 短信入庫
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setCarNumber(chpNo);
		messageInfo.setPlaceName("(" + lon + "," + lat + "");
		messageInfo.setRunType(direction);
		messageServcie.collectDataFromZJ(messageInfo);
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
		String passWord = msg.getMsgBody().readBytes(8).toString(Charset.forName("GBK"));
		String ip = msg.getMsgBody().readBytes(32).toString(Charset.forName("GBK"));
		int port = msg.getMsgBody().readUnsignedShort();

		msg.getMsgBody().clear();
		if (userId != JT809Constants.LOGIN_USER_ID) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_03;
		} else if (!JT809Constants.LOGIN_USER_PASSWORD.equals(passWord)) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_04;
		} else if (!JT809Constants.SERVER_IP.equals(ip.trim())) {
			checkResult = JT809Constants.UP_CONNECT_RSP_ERROR_01;
		}
		Message msgRep = new Message(JT809Constants.UP_CONNECT_RSP);
		ChannelBuffer buffer = ChannelBuffers.buffer(5);
		buffer.writeByte(checkResult);
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

}