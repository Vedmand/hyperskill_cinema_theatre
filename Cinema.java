package cinema;

import java.util.*;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);

    public static void showTheSeats(String[][] matrix) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length + 1; j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " ");
                } else {
                    System.out.print(matrix[i][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static void buyATicket(String[][] matrix) {
        //Input check
        boolean rightInput = false;
        int rowNumber = 0;
        int seatNumber = 0;
        while (!rightInput) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            if (rowNumber >= 0 && rowNumber <= matrix.length) {

            } else {
                System.out.println("Wrong input!");
                System.out.println();
                continue;
            }
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (seatNumber >= 0 && seatNumber <= matrix[0].length) {

            } else {
                System.out.println("Wrong input!");
                System.out.println();
                continue;
            }
            if (matrix[rowNumber - 1][seatNumber - 1] == "B") {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                continue;
            } else {
                rightInput = true;
            }
        }
        //Price
        int ticketPrice;
        if (matrix.length * matrix[0].length <= 60) {
            ticketPrice = 10;
        } else if (rowNumber <= matrix.length / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        
        System.out.println("Ticket price: $" + ticketPrice);
        System.out.println();
        
        matrix[rowNumber - 1][seatNumber - 1] = "B";
    }

    public static void menu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void statistics(String[][] matrix) {
        int boughtTickets = 0;
        int curIncome = 0;
        int totalIncome = 0;
        int ticketPrice;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix.length * matrix[0].length <= 60) {
                    ticketPrice = 10;
                } else if (i < matrix.length / 2) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
                totalIncome = totalIncome + ticketPrice;
                if (matrix[i][j] == "B") {
                    curIncome = curIncome + ticketPrice;
                    boughtTickets++;
                }
            }
        }

        double percentage = (double)boughtTickets / (double)(matrix.length * matrix[0].length);

        System.out.println("Number of purchased tickets: " + boughtTickets);
        String str = String.format("Percentage: %.2f", percentage * 100);
        System.out.println(str + "%");
        System.out.println("Current income: $" + curIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    public static void main(String[] args) {
        //initialization
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsNumber = scanner.nextInt();
        System.out.println();
        
        String[][] matrix = new String[rows][seatsNumber];
        
        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], "S");
        }
        
        //menu
        int choice = 10;
        
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println();
                    showTheSeats(matrix);
                    menu();
                    choice = scanner.nextInt();
                    continue;
                case 2:
                    System.out.println();
                    buyATicket(matrix);
                    menu();
                    choice = scanner.nextInt();
                    continue;
                case 3:
                    System.out.println();
                    statistics(matrix);
                    menu();
                    choice = scanner.nextInt();
                    continue;
                case 0:
                    return;
                default:
                    System.out.println();
                    menu();
                    choice = scanner.nextInt();
                    continue;
            }
        }
    }
}