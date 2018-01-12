import java.util.Scanner;

public class LeaveSystem {

        public static void main (String [] args){

            String [ ] staffNames = new String[2];              // all current staff name
            char [ ] staffPositionCode = new char[2];           // all current staff position code
            int [ ] staffNumLeaves = new int[2];                // all current staff leave balance
            char [ ] positionCode  = {'T', 'M', 'S', 'J'};      // available staff positions
            int  [ ] numLeaves = {52, 28, 21, 14};              // amount of leave for each positions

            String name;                                    // user input staff name
            char newPositionCode;                           // user input staff position code
            int count = 0, choice;                      // count for total staff in system, choice for user selection
            int numDays, leaveLeft;                 //Number of leave applied for and number of leave left
            Scanner sc = new Scanner(System.in);

            do {
                displayMenu();
                choice = sc.nextInt(); sc.nextLine();
                if (choice == 0) break;
                switch (choice) {
                    case 1:
                        if (count == staffNames.length) {
                            System.out.println("No more staff can be added.");
                            break;
                        } else {
                            System.out.print("Enter name of staff to add: ");
                            name = sc.nextLine();
                            System.out.print("Enter position code of staff to add (T, M, S or J): ");
                            newPositionCode = sc.next().toUpperCase().charAt(0);
                            count = addStaff(name, newPositionCode, count, positionCode, staffPositionCode, staffNames, numLeaves, staffNumLeaves);
                            break;
                        }
                    case 2:
                        System.out.print("Enter name of staff to remove: ");
                        name = sc.nextLine();
                        if(removeStaff(name, staffNames, staffPositionCode, staffNumLeaves, count)) {
                            count--;
                            System.out.println("Successfully removed: " + name);
                        }
                        break;
                    case 3:
                        System.out.print("Enter name of staff taking leave: ");
                        name = sc.nextLine();
                        System.out.print("Enter number of days to apply leave for: ");
                        numDays = sc.nextInt(); sc.nextLine();
                        leaveLeft = takeLeave(name, numDays, staffNames, staffNumLeaves, count);
                        if (leaveLeft >= 0 && leaveLeft <= 52) System.out.println(name + " has " + leaveLeft + " day(s) of leave left");
                        break;
                    case 4: listStaffWithLeave(count, staffNames, staffNumLeaves);
                }
            }while (true);

        }

        public static int search(String name, String [] staffNames, int count){
            for (int i = 0; i < count; i++){
                if (name.equalsIgnoreCase(staffNames[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static int addStaff(String name, char newPositionCode, int count, char positionCode [], char staffPositionCode [], String staffNames [], int numLeaves [], int staffNumLeaves []){
            int pos = search(name, staffNames, count);
            if(pos != -1 ) System.out.println(name + " is already a staff!");
            else{
                staffNames[count] = name;
                staffPositionCode [count] = newPositionCode;
                for (int i = 0; i < positionCode.length; i++){
                    if (newPositionCode == positionCode[i]){
                        staffNumLeaves [count] = numLeaves[i];
                    }
                }
                System.out.println("Successfully added " + staffNames[count] + " with " + staffNumLeaves[count] + " days of leave");
                count++;
                return count;
            }
            return count;
        }

        public static boolean removeStaff(String name, String staffNames [], char staffPositionCode [], int staffNumLeaves [], int count){
            int pos = search(name, staffNames, count);
            if (pos == -1){
                System.out.println("Can’t remove " + name + ": Reason: Not a staff");
            }
            else{
                for (int i = pos; i < count-1; i++){
                    staffNames[i] = staffNames[i+1];
                    staffNumLeaves[i] = staffNumLeaves[i+1];
                    staffPositionCode[i] = staffPositionCode[i+1];
                }
                staffNames[count-1] = null;
                staffNumLeaves[count-1] = 0;
                staffPositionCode[count-1] = '\0';
                return true;
            }
            return false;
        }

        public static int takeLeave(String name, int numDays, String [] staffNames, int [] staffNumLeaves, int count){
            int pos = search(name, staffNames, count);
            if (pos == -1){
                System.out.println("Can’t apply leave " + name + ": Reason: Not a staff");
                return Integer.MAX_VALUE;
            }
            else {
                if (numDays > staffNumLeaves[pos]) {
                    System.out.println("Can’t apply leave " + name + ": Reason: Insufficient leave" +
                            "\nApplying " + numDays + " days will result in " + (staffNumLeaves[pos]-numDays) + " days leave!\n"
                            + staffNames[pos] + " can apply only " + staffNumLeaves[pos] + " days(s) of leave");
                    return (staffNumLeaves[pos] - numDays);
                }
                else{
                    staffNumLeaves[pos] = (staffNumLeaves[pos] - numDays);
                    System.out.println("Successful leave application for " + staffNames[pos]);
                    return staffNumLeaves[pos];
                }
            }
        }

        public static void listStaffWithLeave (int count, String staffNames [], int staffNumLeaves []){
            System.out.println("List of Staff who can Take Leave");
            for (int i = 0; i < count; i++)
            {
                if (staffNumLeaves[i] != 0){
                    System.out.printf("%-20s%s days%n", staffNames[i],staffNumLeaves[i]);
                }
            }
            System.out.println("End of List");
        }

        public static void displayMenu(){
            System.out.print("\nMenu\n1. Add Staff\n2. Remove Staff\n3. Take Leave\n4. List Staff Leave Details\n0. Exit\nEnter option: ");
        }

}
