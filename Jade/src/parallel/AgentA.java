package parallel;

import jade.core.Agent;

public class AgentA extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new LoopBehaviour(this, 300));
        addBehaviour(new LoopBehaviour(this, 500));
    }
}
