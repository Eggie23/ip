import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MegatronGriffin {
    public static void main(String[] args) {
        String start = "Uh… hi. It’s Meg. What do you want?\n";
        String end = "Uh… see ya. Try not to forget I exist, I guess.";
        System.out.println(start);

        List<String> list = new ArrayList<>();

        Scanner myObj = new Scanner(System.in);

        String text = myObj.nextLine();

        while (!text.equals("bye")) {
            if (text.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String str = String.format("%s. %s \n", i+1, list.get(i));
                    System.out.println(str);
                }
            } else {
                System.out.println("added: " + text);
                list.add(text);
            }
            text = myObj.nextLine();
        }

        System.out.println(end);

    }
}
