package behaviour;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public abstract class MyReceiver extends SimpleBehaviour {
    private long timeOut, wakeupTime;
    private boolean finished;
    private ACLMessage msg;
    private MessageTemplate mt;

    public MyReceiver(Agent a, int timeout, MessageTemplate mt){
        super(a);
        this.timeOut = timeout;
        this.mt = mt;
    }

    public ACLMessage getMessage(){
        return msg;
    }

    public void reset(int dt) {
        timeOut = dt;
        reset();
    }


    public abstract void handle(ACLMessage msg);

    @Override
    public void onStart(){
        wakeupTime = timeOut < 0 ? Long.MAX_VALUE : System.currentTimeMillis() + timeOut;
    }

    @Override
    public boolean done(){
        return finished;
    }

    @Override
    public void action(){
        msg = mt == null ? myAgent.receive() : myAgent.receive(mt);

        if (msg != null) {
            finished = true;
            handle(msg);
            return;
        }

        long dt = wakeupTime - System.currentTimeMillis();
        if (dt > 0) {
            block(dt);
        }
        else{
            finished = true;
            handle(msg);
        }
    }

    @Override
    public void reset() {
        msg = null;
        finished = false;
        super.reset();
    }
}
