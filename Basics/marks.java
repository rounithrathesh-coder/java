import java.util.*;
public class marks {
    public static void main()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the marks, you scored in your CAT exam: ");
        int mark=sc.nextInt();
        if(mark>=90)
        {
            System.out.println("You have scored Grade A");
        }
        else if(mark>=60)
        {
            System.out.println("You have scored B grade");
        }
        else
        {
            System.out.println("You have failed");
        }

    }
    
}
