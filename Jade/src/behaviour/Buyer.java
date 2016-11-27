package behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sun.plugin2.message.Conversation;

public class Buyer extends Agent {

    protected static int cidCnt = 0;
    private String cidBase;
    private MessageTemplate mt;

    @Override
    protected void setup() {
        ACLMessage msg = newMessage(ACLMessage.QUERY_REF, "", new AID("Seller", AID.ISLOCALNAME));
        mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchConversationId(msg.getConversationId()));
        addBehaviour(new MyReceiver(this, 1000, mt) {
            @Override
            public void handle(ACLMessage msg) {
                System.out.println("Buyer" + (msg == null ? ": TIMEOUT" : " received :" + msg.getContent()));
            }
        });

        send(msg);
    }


    String genCID(){
        if (cidBase==null) {
            cidBase = getLocalName() + hashCode() + System.currentTimeMillis()%10000 + "_";
        }
        return  cidBase + (cidCnt++);
    }

    private ACLMessage newMessage(int perf, String content, AID dest){
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        if (dest != null){
            msg.addReceiver(dest);
        }
        msg.setContent(content);
        return msg;
    }
}
