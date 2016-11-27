package acl;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SenderAgent extends Agent{

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this)
        {
            public void action() {
                ACLMessage msg= receive();
                if (msg!=null)
                    System.out.println( "== Answer" + " <- "
                            +  msg.getContent() + " from "
                            +  msg.getSender().getName() );
                block();
            }
        });

        // Send messages to "a1" and "a2"

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent( "Ping" );
        for (int i = 1; i<=2; i++)
            msg.addReceiver( new AID( "a" + i, AID.ISLOCALNAME) );

        send(msg);
    }
}
