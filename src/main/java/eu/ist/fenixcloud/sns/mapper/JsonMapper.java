package eu.ist.fenixcloud.sns.mapper;

import java.util.Set;

import eu.ist.fenixcloud.sns.domain.Message;
import eu.ist.fenixcloud.sns.domain.Subscription;
import eu.ist.fenixcloud.sns.domain.Topic;

public class JsonMapper implements Mapper {

	public String externalizeTopic(Topic topic) {
		return "{ \"name\": \""+topic.getName()+"\" }";
	}
	
	public String externalizeMessage(Message message) {
		return "{ \"id\": "+message.getOid()+", \"payload\": \""+message.getPayload()+"\" }";
	}
	
	
	public String externalizeTopicSet(Set<Topic> topicSet) {
		String result = "[";
		for(Topic topic : topicSet) {
			result += externalizeTopic(topic)+", ";
		}
	    return result.substring(0, result.length()-2)+"]";
	}

	@Override
	public String externalizeMessageSet(Set<Message> messageSet) {
		String result = "[";
		for(Message message : messageSet) {
			result += externalizeMessage(message);
		}
	    return result.substring(0, result.length()-2)+"]";
	}

	@Override
	public String externalizeSubscription(Subscription subscription) {
		return "{ \"id\": "+subscription.getOid()+", \"callback\": \""+subscription.getCallbackEndpoint()+"\" }";

	}
	
}
