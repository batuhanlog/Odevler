import java.util.Random;
import java.util.Scanner;

// Mayın tarlası oyununu temsil eden MineSweeper sınıfı
public class MineSweeper {
    int rowNumber, colNumber,size;
    int[][] map;
    int[][] board;
    boolean game = true;

    Random rand=new Random();
    Scanner scan=new Scanner(System.in);

    // Mayın Tarlası sınıfının kurucu methodu
    MineSweeper(int rowNumber,int colNumber){
        this.rowNumber=rowNumber;
        this.colNumber=colNumber;
        this.map = new int [rowNumber][colNumber];
        this.board=new int [rowNumber][colNumber];
        this.size=rowNumber*colNumber;

    }

    // Oyunun ana işleyişini yürüten method
    public void run() {
        int row,col,success=0;
        prepareGame();
        print(map);
        System.out.println("Oyun başladı!");
        while(game) {
            print(board);
            System.out.print("Satır Sayısı Giriniz : ");
            row = scan.nextInt();
            System.out.print("Sütun Sayısı Giriniz : ");
            col = scan.nextInt();
            if(row < 0 || row >= rowNumber) {
                System.out.println("Yanlış Kordinat!");
                continue;
            }
            if(col < 0 || col >= colNumber) {
                System.out.println("Yanlış Kordinat!");
                continue;
            }

            if(map[row][col]!= -1) {
                check(row,col);
                success++;
                if(success == (size -(size/4))){
                    System.out.println("TEBRİKLER HİÇ BİR MAYINA BASMADIN");
                    break;
                }
            }else {
                game = false;
                System.out.println("YANDIN! (Üzülme Artık Gazisin!)");
            }
        }
    }

    // Belirtilen konumun etrafındaki mayınları kontrol eden method
    public void check(int r, int c) {
        if(map[r][c] == 0) {
            if((c < colNumber -1) && (map[r][c+1] == -1)) {
                board[r][c]++;
            }
            if((r < rowNumber -3) && (map[r+1][c] == -1)) {
                board[r][c]++;
            }
            if((r > 0) && (map[r-1][c] == -1)) {
                board[r][c]++;
            }
            if((c > 0) && (map[r][c-1] == -1)) {
                board[r][c]++;
            }
            if(board[r][c] == 0) {
                board[r][c] = -2;
            }
        }

    }

    // Oyun haritasını hazırlayan method
    public void prepareGame() {
        int randRow,randCol,count=0;
        for(int i=0;i<=(size/4);i++){
            randRow = rand.nextInt(rowNumber);
            randCol = rand.nextInt(colNumber);
            if(map[randRow][randCol]!= -1) {
                map[randRow][randCol]= -1;
                count++;
            }
        }
    }

    // İki boyutlu bir diziyi ekrana basan method
    public void print(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if(arr[i][j]>=0)
                    System.out.print(" ");
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }


}