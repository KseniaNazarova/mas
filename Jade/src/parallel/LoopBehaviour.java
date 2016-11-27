package parallel;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class LoopBehaviour extends SimpleBehaviour {

    private static String offset = "";
    private static long t0 = System.currentTimeMillis();

    private String tab = "";
    private int n = 1;
    private long dt;

    public LoopBehaviour(Agent agent, long dt){
        super(agent);
        this.dt = dt;
        offset += "\t" ;
        tab = offset;
    }

    @Override
    public void action() {
        System.out.println(tab + (System.currentTimeMillis() - t0) / 10 * 10 + ": " + myAgent.getLocalName());
        block(dt);
        n++;
    }

    @Override
    public boolean done() {
        return n > 6;
    }
}
