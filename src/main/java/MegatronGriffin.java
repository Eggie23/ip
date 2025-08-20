import java.util.Scanner;

public class MegatronGriffin {
    public static void main(String[] args) {
        String start = "Uh… hi. It’s Meg. What do you want?\n";
        String end = "Uh… see ya. Try not to forget I exist, I guess.";
        System.out.println(start);

        Scanner myObj = new Scanner(System.in);

        String text = myObj.nextLine();

        while (!text.equals("bye")) {
            System.out.println(text);
            text = myObj.nextLine();
        }

        System.out.println(end);

    }
}
