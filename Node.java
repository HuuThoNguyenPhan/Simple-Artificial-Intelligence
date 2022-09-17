package Ai_Summer.puzzle;

public class Node {
    int[][] arr = new int[3][3];
    int x,y,f;
    String way;
    Facing canFace;

    public Node(Node node) {
        this.x = node.x;
        this.y = node.y;
        this.f = node.f;
        this.way = node.way;
        this.canFace = node.canFace;
        for(int i = 0; i < node.arr.length; i++)
            this.arr[i] = node.arr[i].clone();
    }

    public Node(int[][] a, String way, Facing canFace, int x, int y, int f) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.way = way;
        this.canFace = canFace;
        for(int i = 0; i < a.length; i++)
            this.arr[i] = a[i].clone();
    }

    int herStic(){
        int sum = 0;
        if(Global.checker == 1){
            if(arr[0][0] != 1) sum++;
            if(arr[0][1] != 2) sum++;
            if(arr[0][2] != 3) sum++;
            if(arr[1][0] != 8) sum++;
            if(arr[1][2] != 4) sum++;
            if(arr[2][0] != 7) sum++;
            if(arr[2][1] != 6) sum++;
            if(arr[2][2] != 5) sum++;
        }else{
            if(arr[0][1] != 1) sum++;
            if(arr[0][2] != 2) sum++;
            if(arr[1][0] != 3) sum++;
            if(arr[1][1] != 4) sum++;
            if(arr[1][2] != 5) sum++;
            if(arr[2][0] != 6) sum++;
            if(arr[2][1] != 7) sum++;
            if(arr[2][2] != 8) sum++;
        }
        return sum + f;
    }

    boolean canMoveLeft(){
        return canFace != Facing.LEFT && y > 0 && Global.cost > herStic();
    }

    boolean canMoveRight(){
        return canFace != Facing.RIGHT && y < 2 && Global.cost > herStic();
    }

    boolean canMoveUp(){
        return canFace != Facing.UP && x > 0 && Global.cost > herStic();
    }

    boolean canMoveDown(){
        return canFace != Facing.DOWN && x < 2 && Global.cost > herStic();
    }

    void moveLeft(){
        int tmp = arr[x][y];
        arr[x][y] = arr[x][y-1];
        arr[x][y-1] =tmp;
        y--;
        canFace = Facing.RIGHT;
        way += "l";
        f++;
    }
    void moveRight(){
        int tmp = arr[x][y];
        arr[x][y] = arr[x][y+1];
        arr[x][y+1] =tmp;
        y++;
        canFace = Facing.LEFT;
        way += "r";
        f++;
    }
    void moveUp(){
        int tmp = arr[x][y];
        arr[x][y] = arr[x-1][y];
        arr[x-1][y] =tmp;
        x--;
        canFace = Facing.DOWN;
        way += "u";
        f++;
    }
    void moveDown(){
        int tmp = arr[x][y];
        arr[x][y] = arr[x+1][y];
        arr[x+1][y] =tmp;
        x++;
        canFace = Facing.UP;
        way += "d";
        f++;
    }

    boolean checkFinish(){
        if(Global.checker == 1){
            for(int i = 0; i <= 2; i ++){
                if(arr[0][i] != i+1 || arr[2][i] != 7-i) return false;
            }
            return arr[1][0] != 8 || arr[1][2] != 4 ? false : true;
        }else{
            for(int i = 0; i <= 2; i ++){
                if(arr[0][i] != i || arr[1][i] != i+3 || arr[2][i] != i+6) return false;
            }
            return true;
        }

    }
}
