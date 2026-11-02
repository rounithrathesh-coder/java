import java.util.Scanner;
public class datatype {
    public static void main(String[] args )
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter short number: ");
        short sh=sc.nextShort();
        
        System.out.print("Enter long number: ");
        long l=sc.nextLong();
        
        System.out.print("Enter a float number: ");
        float f=sc.nextFloat();
        
        System.out.print("Enter double number: ");
        double d=sc.nextDouble();
        
        sc.nextLine();
        System.out.print("Enter string: ");
        String c=sc.nextLine();

        System.out.print("Enter char: ");
        char ch=sc.next().charAt(0);

        System.out.println("The short number is: "+sh);
        System.out.print("The float number is: "+f);
        System.out.println("The float number is: "+f);
        System.out.println("The double number is: "+d);
        System.out.println("The string is: "+c);
        System.out.println("The char is: "+ch);
    }
}

