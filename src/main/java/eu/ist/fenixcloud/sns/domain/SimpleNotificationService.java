package eu.ist.fenixcloud.sns.domain;

import eu.ist.fenixcloud.sns.exception.TopicNameAlreadyInUseException;
import eu.ist.fenixcloud.sns.exception.TopicNotFoundException;

public class SimpleNotificationService extends SimpleNotificationService_Base {

	public SimpleNotificationService() {

	}
	
	/**
	 * Creates a new topic with a unique name
	 * @param name the name that will identify the topic (NOTE: the use of a URN is recommended to avoid collision)
	 * @return the newly created topic
	 * @throws TopicNameAlreadyInUseException when the required name is already being used.
	 */
	public Topic createTopic(String name) throws TopicNameAlreadyInUseException {
		Topic topic = getTopicByName(name);
		if(topic != null) {
			throw new TopicNameAlreadyInUseException(name); 
		}
		topic = new Topic(name);
		addTopic(topic);
		return topic;
	}
	
	/**
	 * Obtains a topic given its name.
	 * @param name the identifying name of the topic 
	 * @return the topic with the name provided, null if no topic exists with the given name
	 */
	public Topic getTopicByName(String name) {
		for(Topic topic : getTopicSet()) {
			if(topic.getName().equals(name)) {
				return topic;
			}
		}
		return null;
	}

	/**
	 * Deletes an existing topic given its name.
	 * @param name the name of the topic to be removed
	 * @throws TopicNotFoundException when the given name has no match among the existing topics
	 */
	public void deleteTopicByName(String name) throws TopicNotFoundException {
		for(Topic topic : getTopicSet()) {
			if(topic.getName().equals(name)) {
				topic.delete();
				return;
			}
		}
		throw new TopicNotFoundException(name);
	}

}
