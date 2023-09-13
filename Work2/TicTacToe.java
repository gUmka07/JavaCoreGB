package java_gb.lesson1;
import java.util.Random;
import java.util.Scanner;

public class Program {
    
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final Scanner scanner = new Scanner(System.in);

    private static final Random random = new Random();

    private static char [][] field;

    private static int fieldSizeX;
    private static int fieldSizeY;

    private static void initialize(){

        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x <  fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY; 
            }  
        }
    }
    private static void printField(){
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++) {
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        }
        System.out.println();

    for (int x = 0; x < fieldSizeX; x++) {
        System.out.print(x + 1 + "|");
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(field[x][y] + "|");
        }
        System.out.println();
    }

    for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
        System.out.print("-");
    }
    System.out.println();
}
public static void main(String[] args) {
    while (true) {
        initialize();
        printField();
        while(true){
            humanTurn();
            printField();
                if (checkGameState(DOT_HUMAN, "Вы победили :)"))
                break;
            aiTurn();
            printField();
                if(checkGameState(DOT_AI, "Победил соперник :("))
                break;
        }
        System.out.print("Играть еще? (Y - да)");
        if(!scanner.next().equalsIgnoreCase("Y"))
            break;
    }
}

    private static void humanTurn() {
        int x, y;
        
        do{
            
            while (true){
                System.out.print("Введите координату x (от 1 до 3): ");
                if (scanner.hasNextInt()){
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                }
                else {
                    System.out.println("Некоректно. Попробуйте снова!");
                    scanner.nextLine();
                }
            }

            while (true){    
                System.out.print("Введите координату y (от 1 до 3): ");
                if (scanner.hasNextInt()){
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                }
                else {
                    System.out.println("Некоректно. Попробуйте снова!");
                    scanner.nextLine();
                }
            }
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
        
    }

    private static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }
    
    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >=0 && y <= fieldSizeY;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);                
        }
        while (!isCellEmpty(x, y));
            field[x][y] = DOT_AI;
    }

    private static boolean checkGameState(char c, String s){
        if(checkWin(c)){
            System.out.println(s);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья");
             return true;
        }
        return false;
    }

    private static boolean checkWin(char c) {
        // проверка по 3м горизонталям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;
        // проверка по 3м горизонталям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;
        // проверка по диагоналям
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }

    private static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }
}
