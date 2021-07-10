import java.util.Scanner;

public class ComputeDay {
    public static void main(String[] args) {

        int day, month, year, century, total=0;

        // day code: 0=saturday, 1=sunday, 2=monday, 3=tuesday, 4=wednesday, 5=thursday, 6=friday
        // month code: jan-1, feb-4, march-4, april-0, may-2, jun-5
        // july-0, aug-3, sep-6, oct-1, nov-4, dec-6
        //year code: 1900-1999=0, 2000-2099=6

        Scanner input = new Scanner(System.in);

        System.out.println("Enter day ");
        day = input.nextInt();
        System.out.println("Enter month ");
        month = input.nextInt();
        System.out.println("Enter year ");
        year = input.nextInt();
        System.out.println("Enter the century code '0' for 1900 -1999 and '6' for 2000-2099 ");
        century = input.nextInt();

        int leapYear = year/4;
        total = day + century + year + leapYear;

        if(month == 1){
            total += 1;
        }
        else if(month == 2){
            total += 4;
        }
        else if(month == 3){
            total = total + 4;
        }
        else if(month == 4){
            total += 0;
        }
        else if(month == 5){
            total += 2;
        }
        else if(month == 6){
            total += 5;
        }
        else if(month == 7){
            total += 0;
        }
        else if(month == 8){
            total += 3;
        }
        else if(month == 9){
            total += 6;
        }
        else if(month == 10){
            total += 1;
        }
        else if(month == 11){
            total += 4;
        }
        else if(month == 12){
            total += 4;
        }
        else System.out.println("Wrong month");

        //Compute day
        if((month>0&&month<=12) && (day>0&&day<=31) && year%4 != 0) {
            if (total % 7 == 0) {
                System.out.println("Your day is Saturday");
            }
            else if(total % 7 == 1){
                System.out.println("Your day is Sunday");
            }
            else if(total % 7 == 2){
                System.out.println("Your day is Monday");
            }
            else if(total % 7 == 3){
                System.out.println("Your day is Tuesday");
            }
            else if(total % 7 == 4){
                System.out.println("Your day is Wednesday");
            }
            else if(total % 7 == 5){
                System.out.println("Your day is Thursday");
            }
            else
                System.out.println("Your day is Friday");
        }
        else if((month>0&&month<=12) && (day>0&&day<=31)  && year%4 ==0 && month==1 || month==2){
            if (total % 7 == 0) {
                System.out.println("Your day is Friday");
            }
            else if(total % 7 == 1){
                System.out.println("Your day is Saturday");
            }
            else if(total % 7 == 2){
                System.out.println("Your day is Sunday");
            }
            else if(total % 7 == 3){
                System.out.println("Your day is Monday");
            }
            else if(total % 7 == 4){
                System.out.println("Your day is Tuesday");
            }
            else if(total % 7 == 5){
                System.out.println("Your day is Wednesday");
            }
            else
                System.out.println("Your day is Thursday");
        }
        else System.out.println("You press wrong data");
    }
}
