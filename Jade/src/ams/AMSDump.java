package ams;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAException;

public class AMSDump extends Agent{

    @Override
    protected void setup() {
        AMSAgentDescription[] agents = null;

        try {
            SearchConstraints sc = new SearchConstraints();
            sc.setMaxResults(-1L); // find all
            agents = AMSService.search(this, new AMSAgentDescription(), sc);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        AID myAID = getAID();
        for (int i = 0; i < agents.length; i++) {
            AID agentID = agents[i].getName();
            System.out.println(agentID.equals(myAID) ? "***" : i + ": " + agentID.getName());
        }

    }
}
