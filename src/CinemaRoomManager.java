import java.util.Scanner;

public class CinemaRoomManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();
        System.out.println();


        boolean exit = true;
        int row = 0;
        int seat = 0;
        int count = 0;
        int income = 0;
        String[][] array = new String[numRows+1][numSeats+1];
        v1(numRows+1, numSeats+1, array);
        while (exit) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printing(numRows+1, numSeats+1, array);
                    break;
                case 2:
                    boolean stop = true;
                    while (stop) {
                        System.out.println("\nEnter a row number:");
                        row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seat = scanner.nextInt();
                        if (row > numRows || seat > numSeats) {
                            System.out.println("Wrong input!");
                        } else if (array[row][seat].equals("B")) {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            adding(row, seat, numRows+1, numSeats+1, array);
                            int price = price(numRows, numSeats, row);
                            System.out.println("Ticket price: $" + price);
                            count++;
                            income = income + price;
                            stop = false;
                        }
                    }
                    break;
                case 3:
                    System.out.printf("\nNumber of purchased tickets: %d\n", count);
                    int total = numRows * numSeats;
                    double percentage = (double) count / (double) total * 100;
                    int totalInc = totalIncome(numRows, numSeats);
                    System.out.printf("Percentage: %.2f%%\n", percentage);
                    System.out.printf("Current income: $%d\n", income);
                    System.out.printf("Total income: $%d\n",totalInc);
                    System.out.println();
                    break;
                case 0:
                    exit = false;
                    break;
            }
        }

    }

    public static void v1(int numRows, int numSeats, String[][] array) {
        array[0][0] = " ";
        for (int i = 1; i < numSeats; i++) {
            String k = String.format("%d", i);
            array[0][i] = k;
        }
        int counter = 0;
        for (int i = 1; i < numRows; i++) {
            counter++;
            String k = String.format("%d", counter);
            array[i][0] = k;
        }
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < numSeats; j++) {
                array[i][j] = "S";
            }
        }
    }

    public static void adding(int row, int seat,int numRows,int numSeats, String[][] array) {
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < numSeats; j++) {
                if (row == i && seat == j) {
                    array[i][j] = "B";
                }
            }
        }
    }
    public static void printing (int numRows, int numSeats, String[][] array) {
        System.out.println("\nCinema:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int price(int numRows, int numSeats, int row) {

        final int max = 10;
        final int min = 8;
        int seats = numRows * numSeats;
        int price = 0;
        int part = 0;
        if (seats <= 60) {
            price = max;
        } else {
            int half = numRows / 2;
            part = (int) Math.floor(half);
            if (row <= part) {
                price = max;
            } else {
                price = min;
            }
            System.out.println();
        }
        return price;
    }

    public static int totalIncome(int numRows, int numSeats) {
        final int max = 10;
        final int min = 8;
        int price = 0;
        double part = 0;
        int seats = numRows * numSeats;
        if (seats <= 60) {
            price = max * seats;
        } else {
            part = Math.floor(numRows/2);
            price = ((int) part * max * numSeats) + ((numRows - (int) part) * min * numSeats);
        }
        return price;
    }
}