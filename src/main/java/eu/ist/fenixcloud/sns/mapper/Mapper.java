package eu.ist.fenixcloud.sns.mapper;

import java.util.Set;

import eu.ist.fenixcloud.sns.domain.Message;
import eu.ist.fenixcloud.sns.domain.Subscription;
import eu.ist.fenixcloud.sns.domain.Topic;

public interface Mapper {

	public String externalizeTopic(Topic topic);
	public String externalizeTopicSet(Set<Topic> topicSet);

	public String externalizeMessage(Message message);
	public String externalizeMessageSet(Set<Message> messageSet);
	public String externalizeSubscription(Subscription subscription);
	
}
