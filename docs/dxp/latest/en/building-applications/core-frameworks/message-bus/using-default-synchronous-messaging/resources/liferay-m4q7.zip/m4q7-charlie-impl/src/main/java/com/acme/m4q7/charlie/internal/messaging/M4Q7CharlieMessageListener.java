package com.acme.m4q7.charlie.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "destination.name=acme/m4q7_able",
	service = MessageListener.class
)
public class M4Q7CharlieMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (_log.isInfoEnabled()) {
			_log.info("Received message payload " + message.getPayload());
		}

		_messageBus.sendMessage(
			message.getResponseDestinationName(),
			new Message() {
				{
					setPayload("M4Q7CharlieMessageListener");
					setResponseId(message.getResponseId());
				}
			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		M4Q7CharlieMessageListener.class);

	@Reference
	private MessageBus _messageBus;

}