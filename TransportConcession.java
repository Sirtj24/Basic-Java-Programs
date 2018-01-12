import java.util.Scanner;

public class TransportConcession {
    public static void main(String[] args) {
        System.out.print("Choose the type of monthly pass\n1 - Primary Student\n2 - Seccondary Student\n3 - Polytechnic Student" +
                "\n4 - University Student\n5 - Full-Time National Serviceman\n6 - Adult\n7 - Senior Citizen"
                + "\n8 - Person with Disability\nYour choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= 8) {
            if (choice >= 6 && choice <= 8) {
                switch (choice) {
                    case 6:
                        System.out.printf("Cost of pass = $%.2f", 120.00);
                        break;
                    case 7:
                    case 8:
                        System.out.printf("Cost of pass = $%.2f", 60.00);
                        break;
                }
            }
            if (choice >= 1 && choice <= 5) {
                System.out.print("B - Bus\n" + "T - Train\n" + "H - Hybrid (Bus and Train)\n" + "Your choice: ");
                char type = sc.next().toUpperCase().charAt(0);
                if (type == 'B' || type == 'T' || type == 'H') {
                    if (choice == 1) {
                        switch (type) {
                            case 'B':
                                System.out.printf("Cost of pass = $%.2f", 22.50);
                                break;
                            case 'T':
                                System.out.printf("Cost of pass = $%.2f", 20.00);
                                break;
                            case 'H':
                                System.out.printf("Cost of pass = $%.2f", 41.00);
                                break;
                        }
                    }
                    if (choice == 2 || choice == 3) {
                        switch (type) {
                            case 'B':
                                System.out.printf("Cost of pass = $%.2f", 27.50);
                                break;
                            case 'T':
                                System.out.printf("Cost of pass = $%.2f", 25.00);
                                break;
                            case 'H':
                                System.out.printf("Cost of pass = $%.2f", 51.00);
                                break;
                        }
                    } else {
                        switch (type) {
                            case 'B':
                                System.out.printf("Cost of pass = $%.2f", 52.00);
                                break;
                            case 'T':
                                System.out.printf("Cost of pass = $%.2f", 45.00);
                                break;
                            case 'H':
                                System.out.printf("Cost of pass = $%.2f", 85.00);
                                break;
                        }
                    }
                } else {
                    System.out.println(type + " is an invalid type of pass.");
                    //System.exit(0);
                }
            }
        }
        else {
            System.out.println(choice + " is an invalid type of person.");
            //System.exit(0);
        }
        System.out.println("\nEnd of program.");
    }
}
