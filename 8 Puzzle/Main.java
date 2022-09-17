package Ai_Summer.puzzle;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        int step = 0;
        Long numOfNode = 0L;
        boolean check = Global.checkFinish();
        Global.initPuzzle();
        String way = "";
        Node node = new Node(Global.puzzle,"",Facing.STAY,Global.posX,Global.posY,0);
        Vector<Node> vt = new Vector<>();
        vt.add(node);
        Global.checker = Global.countStart() % 2;
        System.out.println("Trạng thái ban đầu: ");
        Global.print();
        while(!check && vt.size() != 0){
            Vector<Node> open = new Vector<>();
            int i = vt.size()-1;
            if(vt.get(i).checkFinish()){
                way = vt.get(i).way;
                check = true;
                break;
            }else{
                if(vt.get(i).canMoveUp()){
                    Node nd = new Node(vt.get(i));
                    nd.moveUp();
                    open.add(nd);
                }
                if(vt.get(i).canMoveDown()){
                    Node nd = new Node(vt.get(i));
                    nd.moveDown();
                    open.add(nd);

                }
                if(vt.get(i).canMoveRight()){
                    Node nd = new Node(vt.get(i));
                    nd.moveRight();
                    open.add(nd);
                }
                if(vt.get(i).canMoveLeft()){
                    Node nd = new Node(vt.get(i));
                    nd.moveLeft();
                    open.add(nd);
                }
            }
            vt.remove(vt.size()-1);
            for(i = 0 ; i < open.size() ; i++){
                for (int j = i+1; j < open.size(); j++){
                    if(open.get(i).herStic() <= open.get(j).herStic()){
                        Node tmp = open.get(i);
                        open.set(i,open.get(j));
                        open.set(j,tmp);
                    }
                }
            }
            for(i = 0; i < open.size(); i ++){
                if(open.get(i).herStic() == open.get(open.size()-1).herStic()){
                    vt.add(open.get(i));
                }
            }
            numOfNode ++ ;
        }

        if(!check){
            System.out.println("Thuật toán thất bại, không tìm được đích");
            return;
        }
        for (int i = 0; i < way.length(); i++){
            if(way.toCharArray()[i] == 'l'){
                System.out.println("Di chuyển sang trái");
                Global.moveLeft();
                Global.print();

            }else if(way.toCharArray()[i] == 'r'){
                System.out.println("Di chuyển sang phải");
                Global.moveRight();
                Global.print();

            }else if(way.toCharArray()[i] == 'u'){
                System.out.println("Di chuyển lên trên");
                Global.moveUp();
                Global.print();

            }else if(way.toCharArray()[i] == 'd'){
                System.out.println("Di chuyển xuống dưới");
                Global.moveDown();
                Global.print();

            }
        }
        System.out.println("Thuật toán AKT");
        System.out.println("Số bước đi = " + way.length());
        System.out.println("Số phép toán đã thực hiện = " + numOfNode);
    }

}
