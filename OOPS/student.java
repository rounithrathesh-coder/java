import java.util.*;
public class student{
    String name;
    int roll;
    student(String n,int r){
        name=n;
        roll=r;

    }
    void display(){
        System.out.println(name+" "+roll);

    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        student s=new student("abcd",456);
        s.display();
    }
}