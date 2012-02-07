package eu.ist.fenixcloud.sns.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jvstm.Atomic;

import pt.ist.fenixframework.FenixFramework;

import eu.ist.fenixcloud.sns.Bootstrap;
import eu.ist.fenixcloud.sns.domain.SimpleNotificationService;
import eu.ist.fenixcloud.sns.domain.Subscription;
import eu.ist.fenixcloud.sns.domain.Topic;
import eu.ist.fenixcloud.sns.mapper.JsonMapper;
import eu.ist.fenixcloud.sns.mapper.Mapper;

@Path("topic/{topicName}/subscription")
public class SubscriptionResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public static String subscribeTopic(@PathParam("topicName") String topicName, @FormParam("callbackEndpoint") String callbackEndpoint) {
		Bootstrap.init();
		return createNewSubscriptionToTopic(topicName, callbackEndpoint);
	}
	
	@Atomic
	private static String createNewSubscriptionToTopic(String topicName, String callbackEndpoint) {
		SimpleNotificationService sns = (SimpleNotificationService)FenixFramework.getRoot();
		Topic topic = sns.getTopicByName(topicName);
		Subscription subscription = topic.subscribe(callbackEndpoint);
		Mapper mapper = new JsonMapper();
		return mapper.externalizeSubscription(subscription);
	}
}
