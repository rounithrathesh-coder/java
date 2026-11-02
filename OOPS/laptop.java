import java.util.Queue;

public class laptop{
    String processor;
    String name;
    int ram;
    String colour;

void start()
{
    System.out.println(name+" is starting");
}
void open()
{
    System.out.println(processor+" is opening");
}
public static void main(String args[])
{
    laptop l1= new laptop();
    l1.name="HP";
    l1.processor="intel";
    l1.ram=8;
    l1.colour="white";
    l1.start();
    l1.open();  
}
}