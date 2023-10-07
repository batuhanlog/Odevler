import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    Scanner tara = new Scanner(System.in);
    private int adetSatir;
    private int adetSutun;
    private String[][] mineMap;
    private String[][] board;
    private int sayac = 0;
    private int satirNo;
    private int sutunNo;
    private boolean win;
    private boolean durum;

    // MineSweeper sınıfının kurucu metodu
    MineSweeper(int adetSatir, int adetSutun) {
        this.adetSatir = adetSatir;
        this.adetSutun = adetSutun;
        this.mineMap = new String[adetSatir][adetSutun];
        this.board = new String[adetSatir][adetSutun];
        fill(this.board); // Oyun tahtasını "-" ile doldur
        fill(this.mineMap); // Mayın haritasını "-" ile doldur
    }

    // Rastgele bir satır ve sütun numarası üretme metodu
    public void randomSayi() {
        Random rdn = new Random();
        this.satirNo = rdn.nextInt(this.adetSatir);

        Random rdn2 = new Random();
        this.sutunNo = rdn2.nextInt(this.adetSutun);
    }

    // Verilen bir diziyi "-" ile dolduran metot
    public String[][] fill(String[][] dizi) {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                dizi[i][j] = "-";
            }
        }
        return dizi;
    }

    // Belirtilen koordinatta bir mayın bulunup bulunmadığını kontrol eden metot
    public boolean isFind(String[][] arr, String value) {
        if (arr[this.satirNo][sutunNo] == "*") {
            return false;
        }
        return true;
    }

    // Oyunun ana mantığı olan metot
    public void run() {
        for (int j = 0; j < this.adetSatir; j++) {
            for (int k = 0; k < this.adetSutun; k++) {
                if (sayac < (this.adetSutun * this.adetSatir / 4)) {
                    randomSayi();
                    sayac++;
                    if (isFind(this.board, "*")) {
                        this.mineMap[satirNo][sutunNo] = "*";
                        this.board[satirNo][sutunNo] = "*";
                    } else sayac--;
                }
            }
        }
        printMineMap(); // Mayın haritasını ekrana bas
        fill(this.board); // Oyun tahtasını sıfırla
        printBoard(); // Oyun tahtasını ekrana bas
        koordinatGir(); // Oyuncudan koordinat girmesini iste
    }

    private int durumSayac = 0;

    // Oyuncunun koordinat girmesini ve oyunun ilerleyişini kontrol eden metot
    public void koordinatGir() {
        this.durum = true;
        while (this.durum) {
            int sayac = 0;
            this.durumSayac++;
            boolean kontrol = true;
            int satir = 0;
            int sutun = 0;
            while (kontrol) {
                System.out.println("Satır Giriniz : ");
                satir = tara.nextInt();
                System.out.println("Sütun Giriniz : ");
                sutun = tara.nextInt();
                if ((satir < 0 || satir > (this.adetSatir - 1)) || (sutun < 0 || sutun > (this.adetSutun - 1))) {
                    System.out.println("Hatalı sayı girdiniz lütfen tekrar giriniz ");
                    kontrol = true;
                } else {
                    kontrol = false;
                }
            }
            System.out.println("===========================");
            if (this.mineMap[satir][sutun].equals("*")) {
                System.out.println("Kaybettin ! ");
                printMineMap(); // Mayın haritasını göster
                this.durum = false;
                break;
            }
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((satir + i >= 0 && (satir + i) <= (this.adetSatir - 1)) && (sutun + j >= 0 && (sutun + j) <= (this.adetSutun - 1))) {
                        if (this.mineMap[satir + i][sutun + j].equals("*")) {
                            sayac++;
                            this.board[satir][sutun] = String.valueOf(sayac);
                        }
                        if (sayac == 0) {
                            this.board[satir][sutun] = "0";
                        }
                    }
                }
            }
            if (this.durumSayac == this.adetSatir * this.adetSutun - (this.adetSutun * this.adetSatir / 4)) {
                this.win = true;
            }
            if (this.win == true) {
                this.durum = false;
                System.out.println("KAZANDINIZ !!!");
            } else durum = true;
            printBoard(); // Oyun tahtasını güncel durumuyla ekrana bas
        }
    }

    // Mayın haritasını ekrana basan metot
    public void printMineMap() {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                System.out.print(" " + this.mineMap[i][j]);
            }
            System.out.println("");
        }
        System.out.println("===========================");
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");
    }

    // Oyun tahtasını ekrana basan metot
    public void printBoard() {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                System.out.print(" " + this.board[i][j]);
            }
            System.out.println("");
        }
    }

    // Satır ve sütun sayılarını güncelleyen metot
    public void MineCheck(int satirNo, int sutunNo) {
        this.adetSatir = satirNo;
        this.adetSutun = sutunNo;
    }
}
