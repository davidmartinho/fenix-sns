package eu.ist.fenixcloud.sns.domain;

public class Topic extends Topic_Base {

	public Topic(String name) {
		setName(name);
	}
	
	/**
	 * Publishes a new message on the topic.
	 * @param payload the payload of the message to be posted
	 * @return the message that was published on the topic
	 */
	public Message publishMessage(String payload) {
		Message message = new Message(payload);
		addMessage(message);
		for(Subscription subscription : this.getSubscriptionSet()) {
			subscription.sendNotification(message);
		}
		return message;
	}
	
	/**
	 * 
	 * @param callbackEndpoint
	 * @return
	 */
	public Subscription subscribe(String callbackEndpoint) {
		Subscription subscription = new Subscription(callbackEndpoint);
		addSubscription(subscription);
		return subscription;
	}

	/**
	 * Deletes the topic
	 */
	public void delete() {
		super.deleteDomainObject();
	}
}
