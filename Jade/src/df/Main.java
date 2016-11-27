package df;

public class Main {
    public static void main(String[] args) {
        String[] args1 = {"-gui", "buyer:df.DFBuyer"};
        String[] args2 = {"-container", "seller:df.DFSeller"};
        jade.Boot.main(args1);
        jade.Boot.main(args2);
    }
}
