package com.gamblingsimulation;

public class GamblerSimulator {
//    Instance variables
    static int Account_balance = 100;   // player start betting daily with a stake of 100.
    static int num_of_wins_in_day = 0;
    static int num_of_losses_in_day = 0;

    static int num_of_wins_in_month = 0;
    static int num_of_losses_in_month = 0;
    static int profit = 0;

//    Temporary variables for UC-6
    static int day = 0;
    static int previous_day_win = 0;
    static int previous_day_loss = 0;
    static int luckiest_day;
    static int unluckiest_day;

    //constant variable
    static final int BET = 1;  // player bets 1 for every game.


//    UC-2 :- As a Gambler make 1 bet so either win or loose 1.
    public static void playerWinOrLoose() {
        /* Random check for win or loose checking */
        int random_Check = (int)Math.floor(Math.random()*2);
        switch(random_Check) {
            case 1 :
                num_of_wins_in_month++;  // counting number of wins for whole month.
                num_of_wins_in_day++;     // counting number of wins for every day.
                Account_balance += BET;   // increasing stake/account_balance by 1 if player won the bet.
                break;
            default :
                num_of_losses_in_month++;   // counting number of losses for whole month
                num_of_losses_in_day++;     // counting number of wins for every day.
                Account_balance -= BET;   // decreasing stake/account_balance by 1 if player loose the bet.
        }
    }
//    UC-3 :- As a Calculative Gambler if won or lost 50% of the stake, would resign for the day.
    public static void gambling() {
        int temp_balance = Account_balance;  // temporary variable to hold account balance 100.

        // calculating 50%
        int fifty_percent = (50/100)*temp_balance;

        while(Account_balance <= (Account_balance + fifty_percent) ||
                Account_balance >= (Account_balance - fifty_percent)) {

            playerWinOrLoose();  //calling playerWinOrLoose method.

            if(Account_balance == 150 || Account_balance == 50) {  // if these conditions satisfied then loop will break.
                break;
            }
        }
        System.out.println("Gambler Account balance :- " + Account_balance);
        System.out.println("Gambler total number of wins in a day :- " + num_of_wins_in_day);
        System.out.println("Gambler total number of losses in a day :- " + num_of_losses_in_day);

        lucky_unlucky_day();   // Calling method for UC-6 :- finding luckiest day and unluckiest day


        if(Account_balance == 150) {
            profit += 50;
        }
        else if(Account_balance == 50) {
            profit -= 50;
        }
        System.out.println("Gambler profit : " + profit);


        num_of_wins_in_day = 0;  // making zero bcoz for next day. i want to count from fresh to print every day report.
        num_of_losses_in_day = 0; // making zero bcoz for next day. i want to count from fresh to print every day report
        Account_balance = 100;  // makind 100 bcoz for next day player again starts from 100 only.
    }

    /** UC-5 :- Each month would like to know the days won and lost and by how much. **/
    public static void gambledForMonth() {
        for(day = 1; day <= 30; day++) {     // In UC-5 , it mentioned for a month so changed UC-4=20 days to 30 days.
            System.out.println("\nDAY-" + day + " :-");
            gambling();
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Luckiest day :- " + luckiest_day);
        System.out.println("Un-Luckiest day :- " + unluckiest_day);
        System.out.println("-----------------------------------------------------");
        System.out.println("Gambler total number of wins in a month :- " + num_of_wins_in_month);
        System.out.println("Gambler total number of looses in a month :- " + num_of_losses_in_month);
        System.out.println("-----------------------------------------------------");
    }

// UC-6 :- Would also like to know my luckiest daywhere I won maximum and my unluckiest daywhere I lost maximum. **/
    public static void lucky_unlucky_day() {
        if(previous_day_win < num_of_wins_in_day) {
            previous_day_win = num_of_wins_in_day;    // finding day with maximum wins.
            luckiest_day = day;
        }
        if(previous_day_loss < num_of_losses_in_day) {
            previous_day_loss = num_of_losses_in_day;   // finding day with maximum losses.
            unluckiest_day = day;
        }
    }

//    UC-7 :- If won would like to continue playing next month or stop Gambling.
    public static void gamblerDecision() {
        do {
            gambledForMonth();    // the loop will continue until the gambler profit becomes less than ZERO.
        }while(profit > 0);       // Gambler stops playing when he looses his investment money.
    }


//     Main method(Program execution starts from main method.).
    public static void main(String[] args) {
        System.out.println("@------------WELCOME TO GAMBLING SIMULATOR---------------@\n");
        gamblerDecision();
    }
}

