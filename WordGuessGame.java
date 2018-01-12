import java.util.Scanner;

public class WordGuessGame {

        public static void main(String[] args) {

            String s = "meringue albumen foulard eudaemonic narcolepsy elucubrate"
                    + " vivisepulture pococurante cymotrichous malfeasance";
            String hiddenChars = "";

            Scanner scanString = new Scanner(s);
            int counter = 0, randomNum = (int) (Math.random() * 10);

            do {
                String word = scanString.next();
                if (counter == randomNum) {
                    int charCount = word.length();
                    System.out.println("Spell this " + charCount + "-letter word in 5 tries");
                    for (int i = 0;i < charCount; i++) {
                        hiddenChars += "-";
                    }
                    System.out.println(hiddenChars);
                    guessLetter(charCount, word, hiddenChars);
                }
                counter++;
            }while (counter <= randomNum);
        }

        public static void guessLetter (int charCount, String word, String hiddenChars){

            Scanner scanWord = new Scanner(System.in);
            StringBuilder guessString = new StringBuilder(hiddenChars);
            char correctGuess = ' ';

            for (int loop = 0; loop < 5; loop++) {
                System.out.print("Guess a letter: ");
                char userLetter = scanWord.next().toLowerCase().charAt(0);
                System.out.print("Outcome: ");
                for (int i = 0; i < charCount; i++) {
                    if (userLetter == word.charAt(i)) {
                        guessString.setCharAt(i, userLetter);
                        correctGuess = 'y';
                    }
                }
                System.out.println(guessString);
                if (loop < 4 && correctGuess == 'y'){
                    System.out.print("Do you want spell the word now? (y/n): ");
                    char spellWord = scanWord.next().toLowerCase().charAt(0);
                    if (spellWord == 'y'){
                        System.out.print("Spell the complete word: ");
                        String answer = scanWord.next();
                        checkAnswer(answer, word);
                    }
                }
                if (loop == 4){
                    System.out.print("Spell the complete word: ");
                    String answer = scanWord.next();
                    checkAnswer(answer, word);
                }
            }
        }

        public static void checkAnswer(String answer, String word){
            if (answer.equals(word)){
                System.out.println("You are correct! \nThe correct word is " + word);
                playAgain();
            }
            else {
                System.out.println("You are incorrect! \nThe correct word is " + word);
                playAgain();
            }
        }

        public static void playAgain(){
            Scanner scanWord = new Scanner(System.in);
            System.out.print("Spell another word? (y/n): ");
            char replay = scanWord.next().toLowerCase().charAt(0);
            if (replay == 'y'){
                main(null);
            }
            else {
                System.exit(0);
            }

        }
}
