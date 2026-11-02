import java.util.*;
public class Membership {
    public static void main (String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter name: ");
        String name= sc.nextLine();
        System.out.print("Enter age: ");
        int age=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender=sc.nextLine();
        System.out.print("Enter locality: ");
        String local=sc.nextLine();
        System.out.println("\nName: "+name);
        System.out.println("Age: "+age);
        System.out.println("Gender: "+gender);
        System.out.println("Locality: "+local);


    }


    
}
