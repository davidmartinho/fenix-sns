package eu.ist.fenixcloud.sns.exception;

public class TopicNameAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	private String topicName;
	
	public TopicNameAlreadyInUseException(String topicName) {
		this.topicName = topicName;
	}
	
	public String getTopicName() {
		return this.topicName;
	}
	
}
