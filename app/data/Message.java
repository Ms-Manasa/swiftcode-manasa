package data;

public class Message {
    public String text;
    public enum Sender{USER,BOT}
    public FeedResponse feedResponse;//no new keyword because not allocating memory
    public Sender sender;


}
