package eu.ist.fenixcloud.sns.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jvstm.Atomic;

import pt.ist.fenixframework.FenixFramework;

import eu.ist.fenixcloud.sns.Bootstrap;
import eu.ist.fenixcloud.sns.domain.SimpleNotificationService;
import eu.ist.fenixcloud.sns.domain.Topic;
import eu.ist.fenixcloud.sns.exception.TopicNameAlreadyInUseException;
import eu.ist.fenixcloud.sns.exception.TopicNotFoundException;
import eu.ist.fenixcloud.sns.mapper.JsonMapper;
import eu.ist.fenixcloud.sns.mapper.Mapper;

@Path("topic")
public class TopicResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public static String getAllTheTopics() {
		Bootstrap.init();
		return fetchAllTopics();
	}
	
	@Atomic
	private static String fetchAllTopics() {
		Mapper mapper = new JsonMapper();
		SimpleNotificationService sns = (SimpleNotificationService)FenixFramework.getRoot();
		if(sns.hasAnyTopic()) {
			return mapper.externalizeTopicSet(sns.getTopicSet());
		} else {
			return "{ \"message\": \"No topics were found\" }";
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public static String createTopic(@FormParam("name") String name) {
	    Bootstrap.init();
		return createNewTopic(name);
	}
	
	@Atomic
	private static String createNewTopic(String name) {
		SimpleNotificationService sns = (SimpleNotificationService)FenixFramework.getRoot();
		try {
			Topic topic = sns.createTopic(name);
			Mapper mapper = new JsonMapper();
			return mapper.externalizeTopic(topic);
		} catch(TopicNameAlreadyInUseException e) {
			return "{ \"error\": \"Topic name already in use\" }";
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{topicName}")
	public static String deleteTopic(@PathParam("topicName") String topicName) throws TopicNotFoundException {
	    Bootstrap.init();
		return removeTopic(topicName);
	}
	
	@Atomic
	public static String removeTopic(String topicName) throws TopicNotFoundException {
		SimpleNotificationService sns = (SimpleNotificationService)FenixFramework.getRoot();
		sns.deleteTopicByName(topicName);
		return "{ \"status\": \"success\" }";
	}
}
