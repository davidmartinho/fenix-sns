package eu.ist.fenixcloud.sns.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ist.fenixframework.FenixFramework;

import jvstm.Atomic;

import eu.ist.fenixcloud.sns.Bootstrap;
import eu.ist.fenixcloud.sns.domain.Message;
import eu.ist.fenixcloud.sns.domain.SimpleNotificationService;
import eu.ist.fenixcloud.sns.domain.Topic;
import eu.ist.fenixcloud.sns.mapper.JsonMapper;
import eu.ist.fenixcloud.sns.mapper.Mapper;

@Path("topic/{topicName}/message")
public class MessageResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public static String publishMessage(@PathParam("topicName") String topicName, @FormParam("payload") String messagePayload) {
		Bootstrap.init();
		return postMessage(topicName, messagePayload);
	}
	
	@Atomic
	private static String postMessage(String topicName, String messagePayload) {
		SimpleNotificationService sns = (SimpleNotificationService)FenixFramework.getRoot();
		Topic topic = sns.getTopicByName(topicName);
		Message message = topic.publishMessage(messagePayload);
		Mapper mapper = new JsonMapper();
		return mapper.externalizeMessage(message);
	}
}
