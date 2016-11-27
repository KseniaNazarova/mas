package behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MailerBuyer extends Agent{
    protected static int cidCnt = 0;
    private String cidBase;
    private MessageTemplate mt;

    int bestPrice = 9999;
    ACLMessage bestOffer = null;

    @Override
    protected void setup() {
        ACLMessage msg = newMessage(ACLMessage.QUERY_REF);
        mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchConversationId(msg.getConversationId()));

        SequentialBehaviour sb = new SequentialBehaviour();
        addBehaviour(sb);

        ParallelBehaviour pb = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
        sb.addSubBehaviour(pb);

        for (int i = 1; i <= 3; i++) {
            msg.addReceiver(new AID("Seller" + i, AID.ISLOCALNAME));

            pb.addSubBehaviour(new MyReceiver(this, 1000, mt) {
                @Override
                public void handle(ACLMessage msg) {
                    if (msg != null){
                        int offer = Integer.parseInt(msg.getContent());
                        if (offer < bestPrice){
                            bestPrice = offer;
                            bestOffer = msg;
                        }
                    }
                }
            });
        }

        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                if (bestOffer != null){
                    System.out.println("Best Price $" + bestPrice);
                }
                else {
                    System.out.println("Got no quotes.");
                }
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
        ACLMessage msg = newMessage(perf);
        if (dest != null){
            msg.addReceiver(dest);
        }
        msg.setContent(content);
        return msg;
    }

    private ACLMessage newMessage(int perf){
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }
}
