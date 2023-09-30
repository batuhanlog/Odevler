import java.util.Scanner;

class main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int row,column;
        // Giriş Arayüzü
        System.out.println("M A Y I N  T A R L A S I N A  H O Ş G E L D İ N ");
        System.out.println("===============================================");

        // Kullanıcıdan İlk bilgileri Aldığımız İnput kısmı
        System.out.print("Satir sayisi : ");
        row = scan.nextInt();
        System.out.print("Sütun sayisi : ");
        column= scan.nextInt();

        //MineSweeper sınıfının Main sınıfına tanımlanması
        MineSweeper mayin = new MineSweeper(row,column);

        mayin.run();

    }

}