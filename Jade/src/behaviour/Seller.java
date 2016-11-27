package behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Random;

public class Seller extends Agent {

    MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.QUERY_REF);
    ACLMessage reply;

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive(mt);
                if (msg != null){
                    reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("" + new Random().nextInt(100));
                    int delay = new Random().nextInt(2000);
                    System.out.println(" - " + myAgent.getLocalName() + " <- QUERY from " +
                        msg.getSender().getLocalName() + ". Will Answer in " + delay + "ms.");

                    addBehaviour(new DelayBehaviour(myAgent, delay){
                        @Override
                        public void handleElapsedTimeout() {
                            send(reply);
                        }
                    });
                }
                block();
            }
        });
    }
}
