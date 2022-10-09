package projects.nate.springtest.services;

public class Debug {
    public static void printError(String error) {
        System.out.println("*".repeat(error.length()));
        System.out.println(error);
        System.out.println("*".repeat(error.length()));
    }
}
