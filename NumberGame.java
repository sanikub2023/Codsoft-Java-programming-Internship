import java.util.Random;
import java.util.Scanner;

public class NumberGame{
    public static void main(String[] args) {
    Scanner scanner=new Scanner(System.in);
    Random random = new Random();

    System.out.println("\nWelcome to Number Guess Game");
    System.out.println("\nInstructions :");
     System.out.println("1. Enter Number between 1 to 100");
     System.out.println("2. You have a maximum of 5 attempts to guess the number.");
       
       boolean playagain = true;
       while(playagain){

       int attempt = 5;
       int comval = random.nextInt(100)+1;

       while(attempt>0){
        System.out.println("Enter your guess(Attempts left: "+attempt+"): ");   
        int n = scanner.nextInt();

        if(n>100 || n<1){
           System.out.println("Enter number under 100");
           continue;
            }

           if (n == comval) {
                System.out.println("Congratulations! You've guessed the correct number: " + comval);
                break; 
            } else if (n < comval) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }

            attempt--; 
             if (attempt == 0) {
                System.out.println("Game Over! You've used all your attempts. The correct number was: " + comval);
            }
       
       }

       System.out.println("Restart the Game? (No=1 / Yes=2): ");
       
       int res=scanner.nextInt();

       if(res==1){
        playagain=false;
        System.out.println("Thank you!!");
       }
       }
     

    }
}