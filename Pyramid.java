import java.util.Scanner;

/**
 * Created by Chad on 10/17/16.
 */
public class Pyramid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number");
        int input =scan.nextInt();
        for(int i=1;i<input+1;i++) {
            for(int j=1;j<(input+1)-i;j++) {
                System.out.print(" ");
            }
            for(int k=1;k<=i;k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }

    }
}
