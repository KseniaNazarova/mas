package acl;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;

public class ReceiverAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Receive messages
                ACLMessage msg = receive();
                if (msg != null){
                    JOptionPane.showMessageDialog(null, msg.getContent());
                    System.out.println(( " - " +
                            myAgent.getLocalName() + " received: " +
                            msg.getContent()));
                }
                block();
            }
        });
    }
}
