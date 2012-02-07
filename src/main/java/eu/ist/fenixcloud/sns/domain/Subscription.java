package eu.ist.fenixcloud.sns.domain;

public class Subscription extends Subscription_Base {

	public Subscription(String callbackEndpoint) {
		setCallbackEndpoint(callbackEndpoint);
	}

	public void sendNotification(Message message) {
		//TODO: SEND NOTIFICATION HERE
	}
}
