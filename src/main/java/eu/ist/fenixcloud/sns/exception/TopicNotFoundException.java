package eu.ist.fenixcloud.sns.exception;

public class TopicNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String topicName;
	
	public TopicNotFoundException(String topicName) {
		this.topicName = topicName;
	}
	
	public String getTopicName() {
		return topicName;
	}
	
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}
