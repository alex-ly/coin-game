import java.util.*;

public class CoinGame{
  public static void main(String[] args){
    int response, coins;
    System.out.println("Coin game: take turns taking either 1, 2, or 3 coins. The player who takes the last coin wins.");

    System.out.println("Are you playing against another player or the AI? Input 1 for player, 2 for AI");
    Scanner scan=new Scanner(System.in);

    do{
      response=scan.nextInt();
      if(response<1 || response>2){
        System.out.println("Invalid response");
      }
    }while(response<1 || response>2);

    System.out.println("How many coins will there be (min 10)?");
    do{
      coins=scan.nextInt();
      if(coins<10){
        System.out.println("Too few coins.");
      }
    }while(coins<10);

    if(response==1){

      System.out.println("Which player is going first? Input 1 for player 1 and 2 for player 2");
      do{
        response=scan.nextInt();
        if(response<1 || response>2){
          System.out.println("Invalid response");
        }
      }while(response<1 || response>2);
      vsPlayer(response, coins, scan);

    }else{

      System.out.println("Select difficulty. 1=easy 2=normal 3=expert");
      int difficulty;
      do{
        difficulty=scan.nextInt();
        if(difficulty<1 || difficulty>3){
          System.out.println("Invalid response");
        }
      }while(difficulty<1 || difficulty>3);

      System.out.println("Which player is going first? Input 1 for player 1 and 2 for AI");

      do{
        response=scan.nextInt();
        if(response<1 || response>2){
          System.out.println("Invalid response");
        }
      }while(response<1 || response>2);

      vsAI(response, coins, scan, difficulty);

    }

    scan.close();
  }

  public static void vsPlayer(int response, int coins, Scanner scan){

    boolean player1turn;
    if (response==1){
      player1turn=true;
    }else{
      player1turn=false;
    }

    while(coins>0){

      if(player1turn){
        System.out.println("Input how many coins you will take, Player 1");
      }else{
        System.out.println("Input how many coins you will take, Player 2");
      }

      do{
        response=scan.nextInt();
        if(response<1 || response>3 || coins-response<0){
          System.out.println("Invalid response");
        }
      }while(response<1 || response>3 || coins-response<0);

      coins -= response;

      if(player1turn){
        System.out.println("Player 1 has taken "+response+" coins.");
        player1turn=false;
      }else{
        System.out.println("Player 2 has taken "+response+" coins.");
        player1turn=true;
      }

      System.out.println(coins+" coins remain");

    }
    if(player1turn){
      System.out.println("Player 2 wins");
    }else{
      System.out.println("Player 1 wins");
    }
  }

  public static void vsAI(int response, int coins, Scanner scan, int difficulty){

    boolean player1turn;
    if (response==1){
      player1turn=true;
    }else{
      player1turn=false;
    }

    while(coins>0){

      if(player1turn){

        System.out.println("Input how many coins you will take, Player 1");
        do{
          response=scan.nextInt();
          if(response<1 || response>3 || coins-response<0){
            System.out.println("Invalid response");
          }
        }while(response<1 || response>3 || coins-response<0);

      }else{
        response=AIturn(coins, difficulty);
      }

      coins -= response;

      if(player1turn){
        System.out.println("Player 1 has taken "+response+" coins.");
        player1turn=false;
      }else{
        System.out.println("The AI has taken "+response+" coins.");
        player1turn=true;
      }

      System.out.println(coins+" coins remain");

    }
    if(player1turn){
      System.out.println("AI wins");
    }else{
      System.out.println("Player 1 wins");
    }

  }

  public static int AIturn(int coins, int difficulty){

    Random r=new Random();
    int response;
    if(coins<4){
      return coins;
    }

    if(difficulty==1){
      response=r.nextInt(3)+1;

    }else if(difficulty==2){

      if(coins % 4 ==0){
        response=r.nextInt(3)+1;
      }else{
        if(coins<10){
          response=coins % 4;
        }else{
          response=r.nextInt(3)+1;
        }
      }

    }else{
      if(coins % 4 ==0){
        response=r.nextInt(3)+1;
      }else{
        response=coins % 4;
      }
    }

    return response;
  }


}
