import java.util.*;

public class nestedLoop {
    public static void main(String[] args)
    {
        for(int customer=1;customer<=10;customer++){
            System.out.print("Customer: )"+customer);
            for(int burger=1;burger<=4;burger++)
            {
                System.out.print("Preparing burger:"+burger);
            }
        }
    }  
}
