package behaviour;

public class Main {
    public static void main(String[] args) {
        String[] args1 = {"-gui", "Seller1:behaviour.Seller;" +
                "Seller2:behaviour.Seller;" +
                "Seller3:behaviour.Seller;" +
                "Fred:behaviour.MailerBuyer"};
        String[] args2 = {"-container", "Jack:behaviour.MailerBuyer"};
        jade.Boot.main(args1);
        jade.Boot.main(args2);
    }
}


/*
 - Seller1 <- QUERY from Fred. Will Answer in 431ms.
 - Seller3 <- QUERY from Fred. Will Answer in 523ms.
 - Seller2 <- QUERY from Fred. Will Answer in 1566ms.
 Best Price $19

 - Seller1 <- QUERY from Jack. Will Answer in 869ms.
 - Seller2 <- QUERY from Jack. Will Answer in 944ms.
 - Seller3 <- QUERY from Jack. Will Answer in 642ms.
Best Price $14
 */