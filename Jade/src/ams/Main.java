package ams;

public class Main {
    public static void main(String[] args) {
        String[] args1 = {"-gui", "main1:acl.SenderAgent;a1:acl.ReceiverAgent;a2:acl.PongAgent"};
        String[] args2 = {"-container", "dump:ams.AMSDump"};
        jade.Boot.main(args1);
        jade.Boot.main(args2);
    }
}
