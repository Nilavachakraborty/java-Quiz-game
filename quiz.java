
import java.util.Scanner;

public class quiz{
    public static void main(String[] args){

    //quiz game 
    //questions arry[]
    //options array[]
    //declare varibales
    //welcome mesagfe
    //question loop
    //options
    //get guess from user
    //check the guess
    //display the final rsult 
    String[] questions={"what is the main funvtion of a router",
                        " which part of the comouter is known as the brain",
                        "what year was facebook launches",
                        "who is known as the father of computer ",
                        "what was the fast programming language"};

    String[][] options={
                        {"1. storing files", "2. encrypting files","3. Directing internet traffic ", "4. managing passwords"},
                        {"1. CPU", "2. hard drive ", "3. RAM", "4. GPU"},
                        {"1. 2000","2. 2004","3. 2007","4. 2100"},
                        {"1. steve jobs","2. nilava","3. aditya ","4. shyam"},
                        {"1. python","2. C", "3. C++", "4. fortran"}};


            //choocing the correct answers and atoring them 
            int[] answers={3,1,2,2,4};
            int score=0;
            int guess;
            Scanner sc = new Scanner(System.in);
            System.out.println("welcome to the quiz game");

        for(int i=0; i<questions.length; i++) {
                System.out.println(questions[i]);

                for(String option : options[i]){
                    System.out.println(option);
                }

                System.out.println("Enter your guess");
                guess= sc.nextInt();


                if(guess==answers[i]){
                    System.out.println("CORRECT ANSWERS");
                    score++;
                }else{
                    System.out.println("incorrect answrrs");
                }
                    

        }
        System.out.println("final score is "+score);


        











            sc.close();

    
    }

}
