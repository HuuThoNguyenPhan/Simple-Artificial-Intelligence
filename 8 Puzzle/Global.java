package Ai_Summer.puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Global {
    static int posX;
    static int posY;
    static int checker;
    static int cost;
    static int[][] puzzle = new int[3][3];

    public static List<Integer> find(int value){
        for (int i = 0; i <=2; i++)
            for (int j = 0; j <=2; j++){
                if(puzzle[i][j]==value)
                    return Arrays.asList(i,j);
            }
        return null;
    }
    public static void swap(int x, int y){
        var posX = find(x);
        var posY = find(y);
        var tmp = puzzle[posX.get(0)][posX.get(1)];
        puzzle[posX.get(0)][posX.get(1)] = puzzle[posY.get(0)][posY.get(1)];
        puzzle[posY.get(0)][posY.get(1)] = tmp;
    }

    static void moveLeft(){
        swap(puzzle[posX][posY] , puzzle[posX][posY-1]);
        posY--;
    }
    static void moveRight(){
        swap(puzzle[posX][posY] , puzzle[posX][posY+1]);
        posY++;
    }
    static void moveUp(){
        swap(puzzle[posX][posY] , puzzle[posX-1][posY]);
        posX--;
    }
    static void moveDown(){
        swap(puzzle[posX][posY] , puzzle[posX+1][posY]);
        posX++;
    }

    static void initPuzzle(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập các giá trị cho puzzle");
        for(int i=0; i<=2; i++)
            for(int j=0; j<=2; j++) {
                System.out.print("Nhập phần tử thứ [" + i + ", " + j + "]: ");
                puzzle[i][j] = sc.nextInt();
            }
        System.out.print("Nhập chi phí tối đa của thuật toán = ");
        cost = sc.nextInt();
        boolean checked = true;
        int sum = 0;
        for (int i = 0; i <= 2 ; i++){
            for (int j = 0; j <= 2 ; j++){
                sum += puzzle[i][j];
                if(puzzle[i][j] > 8) {
                    checked = false;
                    break;
                }
            }
        }
        if(sum != 36 || checked == false){
            System.out.println("Nhập sai dữ liệu vui lòng nhập lại");
            initPuzzle();
            return;
        }
        for (int i = 0; i <= 2 ; i++){
            for (int j = 0; j <= 2 ; j++){
                if(puzzle[i][j] == 0){
                    posX = i;
                    posY = j;
                    return;
                }
            }
        }
    }

    static void print(){
        for (int i = 0; i <= 2 ; i++){
            for (int j = 0; j <= 2 ; j++){
                System.out.print(puzzle[i][j] + "\t");
                if(j==2){
                    System.out.println("\n");
                }
            }
        }
    }

    static boolean checkFinish(){
        int counter1 = 0, counter2 = 0;
        for (int i = 0; i <= 2 ; i++){
            if(puzzle[0][i] == i+1) counter1++;
        }

        for (int i = 0; i <= 2 ; i++){
            if(puzzle[2][i] == 7-i) counter1++;
        }
        if(puzzle[1][0] == 8){
            counter1++;
        }
        if(puzzle[1][2] == 4) {
            counter1++;
        }
        if(counter1 == 8) return true;

        for (int i = 0; i <= 2 ; i++){
            if(puzzle[0][i] == i) counter2++;
            if(puzzle[1][i] == i+3) counter2++;
            if(puzzle[2][i] == i+6) counter2++;
        }

        if(counter2 == 8) return true;
        return false;
    }

    static int countStart(){
        int sum = 0;
        for (int q = 0; q <= 8 ; q++){
            int row = q/3;
            int col = q%3;
            int counter = puzzle[row][col];
            for (int i = 0; i <= 2 ; i++){
                for (int j = 0; j <= 2 ; j++){
                    if( (row < i && puzzle[i][j] < counter && puzzle[i][j] != 0 )  ){
                        sum++;
                    }else if(row == i && col < j && puzzle[i][j] < counter && puzzle[i][j] != 0){
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
}
