import java.util.*;
public class vote {
    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter your age: ");
        int a= sc.nextInt();
        if(a>=18)
        {
            System.out.println("You are elgible to vote..");

        }
        else{
            System.out.print("You are not eligible to vote..");
        }

    }
    
}
