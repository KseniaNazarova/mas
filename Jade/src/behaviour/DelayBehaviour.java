package behaviour;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class DelayBehaviour extends SimpleBehaviour {

    private long timeout, wakeupTime;
    private boolean finished;

    public DelayBehaviour(Agent a, long timeout){
        super(a);
        this.timeout = timeout;
        finished = false;
    }

    public void reset(long timeout) {
        wakeupTime = System.currentTimeMillis() + timeout;
        finished = false;
    }

    public void handleElapsedTimeout(){};

    @Override
    public void onStart() {
        wakeupTime = System.currentTimeMillis() + timeout;
    }

    @Override
    public void action() {
        long dt = wakeupTime - System.currentTimeMillis();
        if (dt > 0){
            block(dt);
        }
        else {
            finished = true;
            handleElapsedTimeout();
        }
    }

    @Override
    public boolean done() {
        return finished;
    }
}
