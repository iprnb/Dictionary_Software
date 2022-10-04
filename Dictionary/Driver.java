package Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Driver{
    
    public static void menu(Word[] word){
        Word w = new Word();
        Scanner input = new Scanner(System.in);
        String in;
        int counter =0;
        String localDir = System.getProperty("user.dir");
        System.out.println("0 : Look up a word | 1 : Add a new word | 2 : Update a word | 3 : Exit");
        System.out.print("input: ");
        in = input.nextLine();
        if(in.equals("0")){
            word = w.findWord(word);
            menu(word);
        } else if(in.equals("1")){
            word = w.addWord(word);
            menu(word);
        } else if (in.equals("2")){
            word = w.updateWord(word);
            menu(word);
        } else if(in.equals("3")){
            System.out.println("Thanks for using this dictionary. Have a great day!");
            for (int i = 0; i < 100; i++) {
                if (word[i] == null)
                    break;
                counter++;
            }
            try {
                FileWriter newf = new FileWriter(localDir + "\\HW5\\dictionary.txt");
                for(int i=0; i<counter; i++){
                    int z=0;
                    newf.write(word[i].word+" : ");
                    newf.write(word[i].def[0]);
                    z++;
                    while(word[i].def[z]!=null&&z<10){
                        newf.write(" , "+word[i].def[z]);
                        z++;
                    }
                    newf.write("\n");
                }
                newf.close();
            } catch (IOException e) {
                System.out.println("IO Expection error");
            }
        } else{
            System.out.println("Unknown command.");
            menu(word);
        }
    }

    public static String input(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static Word[] getDictionary(){
        String localDir = System.getProperty("user.dir");
        File df = new File(localDir+"\\HW5\\dictionary.txt");
        Word words[] = new Word[100];
        String line;
        int wordsc =0;
        try{
            Scanner s1= new Scanner(df);
            while(s1.hasNextLine()){
                words[wordsc] = new Word();
                line = s1.nextLine();
                words[wordsc].setWord(line.split(" : ")[0]);
                words[wordsc].setDef(line.split(" : ")[1].split(" , "));
                wordsc++;
            }
            return words;
        } catch(FileNotFoundException e){
                System.out.println("File not found!");
                return words;
            }
    }
    public static void main(String[] args) {
        User[] users = new User[100];
        int usersc=0;
        String localDir = System.getProperty("user.dir");
        Scanner input = new Scanner(System.in);
        String pass, username;
        boolean login=false;
        File uf = new File(localDir+"\\HW5\\users.txt");
        try{
            Scanner s = new Scanner(uf);
            String line;
            while(s.hasNextLine()){
                users[usersc] = new User();
                line= s.nextLine();
                users[usersc].setUsername(line.split(" ")[0]);
                users[usersc].setPass(line.split(" ")[1]);
                usersc++;
            }
            System.out.println("<LOGIN MENU>\n");
            System.out.print("Enter Username:");
            username = input.nextLine();
            System.out.print("Enter Password:");
            pass = input.nextLine();
            for(int i=0; i<usersc; i++){
                if(users[i].login(username,pass)){
                    System.out.println("You're logged in!");
                    login =true;
                    break;
                }
            }
            if(login==false)
                System.out.println("Username or password is incorrect!");
            else{
                    System.out.println("\n<USER MENU>\n");
                    menu(getDictionary());
            }
        }   catch(FileNotFoundException e){
                System.out.print("File not found");
        }    
    }
}
