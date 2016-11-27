package df;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class DFSeller extends Agent{

    @Override
    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Buyer");
        dfd.addServices(sd);

        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            System.out.println(result.length + " results.");
            if (result.length > 0)
                System.out.println("\t" + result[0].getName());

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
