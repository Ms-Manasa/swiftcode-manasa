package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;

public class MessageActor extends UntypedActor {
    private final ActorRef out;
    public FeedService feedService = new FeedService();
    public NewsAgentService newsAgentService = new NewsAgentService();
    //public FeedResponse feedResponse=new FeedResponse();


    //self-reference the actor//
    //props//
    //objects of feed service and news agent service
    //define another actor reference//
    @Override
    public void onReceive(Object message) throws Throwable {
        //send back the response
        ObjectMapper objectMapper = new ObjectMapper();
        Message messageObject = new Message();

        if (message instanceof String) {

            messageObject.text = (String) message;
            messageObject.sender = Message.Sender.USER;
            out.tell(objectMapper.writeValueAsString(messageObject), self());
            String query = newsAgentService.getNewsAgentResponse("Find " + message, UUID.randomUUID()).query;
            FeedResponse feedResponse = feedService.getFeedByQuery(query);
            messageObject.text = (feedResponse.title == null) ? "No Results found" : "Showing results for:" + query;
            messageObject.feedResponse = feedResponse;
            messageObject.sender = Message.Sender.BOT;
            out.tell(objectMapper.writeValueAsString(messageObject), self());

        }
        else{
            messageObject.text="Invalid";
            messageObject.sender=Message.Sender.BOT;
            out.tell(objectMapper.writeValueAsString(messageObject), self());

        }


    }

    public static Props props(ActorRef out) {//class props reference
        return Props.create(MessageActor.class, out);
    }

    public MessageActor(ActorRef out) {
        this.out = out;
    }
}