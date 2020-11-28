import java.util.Scanner;

public class User {
    private String name;
    private int winnings = 0;

    public User() {
        this.winnings = 0;
    }

    public int draw(){
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        boolean istrue = true;

        while(istrue) {
            System.out.print ("Wykonaj ruch: ");
            String choice = scanner.nextLine();
            switch(choice) {
                case "1":
                    System.out.println(getName() + " zagrał/a kamień");
                    result = 0;
                    istrue = false;
                    break;
                case "2":
                    System.out.println(getName() + " zagrał/a papier");
                    result = 1;
                    istrue = false;
                    break;
                case "3":
                    System.out.println(getName() + " zagrał/a nożyce");
                    result = 2;
                    istrue = false;
                    break;
                case "x":
                    result = 3;
                    istrue = false;
                    break;
                case "n":
                    result = 4;
                    istrue = false;
                    break;
                default:
                    System.out.println("Użyto niedozwolonego klawisza");

            }
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
