import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> cards = new ArrayList<Integer>();
    static Scanner sc = new Scanner(System.in);
    static int PlayerPoint = 0;
    static int BotPoint = 0;
    static boolean PlayerPass = false;
    static boolean BotPass = false;

    public static void main(String[] args) {
        ArrayCards();
    }

    static void ArrayCards() {
        for (int i = 1; i < 12; i++) {
            cards.add(i);
        }
        PlayerTakeCards();
    }

    static void PlayerTakeCards() {
        System.out.println("Do you want to draw a card? (y/n)");
        int RandomCardIndex = (int) (Math.random() * cards.size()); //Отсчет начинается с 0 в списке cards и до размерности списка
        int CardAmount;
        String PlayerTakeCard = sc.next();
        switch (PlayerTakeCard) {
            case "y":
                PlayerPass=false;
                CardAmount = cards.get(RandomCardIndex);
                if (PlayerPoint > 21) {
                    System.out.println("You already have more than 21 points, showing results");
                    PlayerPass=true;
                    CheckResults();
                }
                else {
                    if (cards.size() < 1)
                        CheckResults();
                    System.out.println("You get " + CardAmount + " points");
                    PlayerPoint += CardAmount;
                    System.out.println("Your total score = " + PlayerPoint);
                    cards.remove(RandomCardIndex);
                    BotTakeCards();
                }
                break;
            case "n":
                PlayerPass = true;
                System.out.println("You pass");
                if (PlayerPass && BotPass)
                    CheckResults();
                else
                    BotTakeCards();
                break;
            default:
                System.out.println("You entered an invalid value");
                PlayerTakeCards();
                break;
        }
    }


    static void BotTakeCards() {
        System.out.println("Now bot take a random card");
        BotLogic();
        System.out.println("Left in the deck " + cards.size() + "/11" + " cards");
        if(PlayerPass==true && BotPass==true)
            CheckResults();
        else
        PlayerTakeCards();
    }

    static void BotLogic() {
        int BotTakeCardRand = (int) (Math.random() * cards.size());
        int BotCardAmount;
        BotCardAmount = cards.get(BotTakeCardRand);
        if (BotPoint >= 16) {
            BotPass = true;
            System.out.println("Bot pass");
        } else {
            BotPoint += BotCardAmount;
            cards.remove(BotTakeCardRand);
        }
    }

    static void CheckResults() {
        if (PlayerPoint > BotPoint && PlayerPoint <= 21 && BotPoint <= 21) {
            System.out.println("");
            System.out.println("======================");
            System.out.println("Player won by typing " + PlayerPoint + " score");
            System.out.println("Bot score = " + BotPoint);
            System.out.println("======================");
        }
        else if (PlayerPoint <=0 && BotPoint >0) {
            System.out.println("");
            System.out.println("======================");
            System.out.println("Bot won by typing " + BotPoint + " score");
            System.out.println("!Player has never drawn a card!");
            System.out.println("======================");
        }
        else if (PlayerPoint < BotPoint && PlayerPoint <=21 && BotPoint <=21){
            System.out.println("");
            System.out.println("======================");
            System.out.println("Bot won by typing " + BotPoint + " score");
            System.out.println("Player score = " + PlayerPoint);
            System.out.println("======================");
        }
        else if (PlayerPoint == BotPoint || PlayerPoint >21 && BotPoint >21) {
            System.out.println("");
            System.out.println("Draw! Nice game");
            System.out.println("======================");
            System.out.println("Player score = "+ PlayerPoint);
            System.out.println("Bot score = "+ BotPoint);
            System.out.println("======================");
        }
        else if (PlayerPoint > 21 && BotPoint <=21) {
            System.out.println("");
            System.out.println("======================");
            System.out.println("Bot won by typing " + BotPoint + " score");
            System.out.println("Player score = " + PlayerPoint);
            System.out.println("======================");
        }
        else if (PlayerPoint <=21 && BotPoint >21) {
            System.out.println("");
            System.out.println("======================");
            System.out.println("Player won by typing " + PlayerPoint + " score");
            System.out.println("Bot score = " + BotPoint);
            System.out.println("======================");
        }
    }
}
