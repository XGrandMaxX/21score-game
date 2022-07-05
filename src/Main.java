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
                CardAmount = cards.get(RandomCardIndex);
                if(PlayerPoint >21)
                    CheckResults();
                else {
                    System.out.println("You get " + CardAmount + " points");
                    PlayerPoint += CardAmount;
                    System.out.println("Your total score = " + PlayerPoint);
                    cards.remove(RandomCardIndex);
                }
                if (cards.size() < 1) {
                    CheckResults();
                }
                else
                    BotTakeCards();
                break;
            case "n":
                PlayerPass = true;
                System.out.println("You pass");
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
        if(BotPoint>=16)
            CheckResults();
        else
            PlayerTakeCards();
    }

    static void BotLogic() {
        int BotTakeCardRand = (int) (Math.random() * cards.size());
        if (BotPoint >= 16) {
            BotPass = true;
            System.out.println("Bot pass");
        } else {
            BotPoint += cards.get(BotTakeCardRand);
            cards.remove(BotTakeCardRand);
        }
    }

    static void CheckResults() {
        if (PlayerPoint > BotPoint && PlayerPoint <22 || PlayerPoint==21) {
            System.out.println("");
            System.out.println("Player won by typing " + PlayerPoint + " score");
            System.out.println("Bot score = "+BotPoint);
        }
        else if (BotPoint > PlayerPoint && BotPoint <22 || BotPoint==21) {
            System.out.println("");
            System.out.println("Bot won by typing " + BotPoint + " score");
            System.out.println("Player score = "+PlayerPoint);
        }
        else if (PlayerPoint > 21 && BotPoint > 21 || PlayerPoint == BotPoint) {
            System.out.println("");
            System.out.println("Draw! Nice game");
        }
        else if (PlayerPoint >21 && BotPoint <22) {
            System.out.println("");
            System.out.println("Bot won by typing " + BotPoint + "score");
            System.out.println("Player score = "+PlayerPoint);
        }
        else if (BotPoint >21 && PlayerPoint <22) {
            System.out.println("");
            System.out.println("Player won by typing " + PlayerPoint + " score");
            System.out.println("Bot score = "+BotPoint);
        }
        else if (PlayerPass && BotPass) {
            System.out.println("" + "\n" + "==================");
            System.out.println("Player score = " + PlayerPoint + "\n" + "Bot score = " + BotPoint); //Результаты, у кого больше, тот и победил
            System.out.println("==================");
        }
        else
            PlayerTakeCards();
    }
}