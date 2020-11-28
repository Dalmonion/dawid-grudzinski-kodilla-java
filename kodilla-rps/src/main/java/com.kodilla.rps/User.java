import java.util.Scanner;

public class User {
    private String name;
    private int winnings = 0;

    public User() {
        this.winnings = 0;
    }

    public int draw(){
        int result = -1;
        System.out.print ("Wykonaj ruch: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch(choice) {
            case "1":
                System.out.println("Zagrano kamień");
                result = 0;
                break;
            case "2":
                System.out.println("Zagrano papier");
                result = 1;
                break;
            case "3":
                System.out.println("Zagrano nożyce");
                result = 2;
                break;
        }
        return result;
    }

    public int getWinnings() {
        return winnings;
    }

    public void addWinnings() {
        this.winnings ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
