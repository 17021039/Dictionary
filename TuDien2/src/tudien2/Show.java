
package tudien2;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import static tudien2.Logic.*;


public class Show {
    static Scanner sc = new Scanner(System.in);
    
    // in ra tu, nghia vua tim.
    public static void showFind() {
        System.out.println("Bạn có muốn tìm từ(nghĩa)?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Bạn muốn tìm theo từ hay nghĩa?");
            System.out.println("1. Từ.");
            System.out.println("2. Nghĩa.");
            choose = Integer.parseInt(sc.nextLine());
            continueFind(choose);
        }
    }
    
     // tiep tuc tim.
    public static void continueFind(int choose) {
        Scanner sc = new Scanner(System.in);
        String find;
        if(choose == 1) {
            System.out.print("Nhập từ bạn muốn tìm: ");
            find = sc.nextLine();
            if(findWord(find.toLowerCase(), f).equals("NOT FOUND"))
                System.out.println("Từ bạn tìm không có trong từ điển.");
            else
                System.out.println("Nghĩa của từ " + find + " là: " + findWord(find.toLowerCase(), f));
        } else {
            System.out.print("Nhập nghĩa bạn muốn tìm: ");
            find = sc.nextLine();
            if(findMean(find.toLowerCase(), f).equals("NOT FOUND"))
                System.out.println("Từ bạn tìm không có trong từ điển.");
            else
                System.out.println("Những từ có nghĩa " + find + " là: " + findMean(find.toLowerCase(), f));
        }
        
        System.out.println("Bạn có muốn tiếp tục tìm?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Bạn muốn tìm theo từ hay nghĩa?");
            System.out.println("1. Từ.");
            System.out.println("2. Nghĩa.");
            choose = Integer.parseInt(sc.nextLine());
            continueFind(choose);
        }
    }
    
     // sua tu, nghia trong File.
    public static void showRepair() {
        System.out.println("Bạn có muốn sửa từ, nghĩa trong Từ Điển không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Bạn muốn sửa từ hay sửa nghĩa?");
            System.out.println("1. Từ.");
            System.out.println("2. Nghĩa.");
            choose = Integer.parseInt(sc.nextLine());
            continueRepair(choose);
        }
    }
    
     // tiep tuc sua neu muon sua tiep.
    public static void continueRepair(int choose) {
        String incorrect,correct;
        if(choose == 1 ) {
            System.out.print("Nhập từ sai: ");
            incorrect = sc.nextLine();
            System.out.print("Nhập từ đúng: ");
            correct = sc.nextLine();
            repairWord(incorrect.toLowerCase(), correct.toLowerCase(), f);
        } else {
            System.out.print("Nhập từ có nghĩa sai: ");
            incorrect = sc.nextLine();
            System.out.print("Nhập nghĩa đúng: ");
            correct = sc.nextLine();
            repairMean(incorrect.toLowerCase(), correct.toLowerCase(), f);
        }
        
        System.out.println("Bạn có muốn sửa tiếp không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Bạn muốn sửa từ hay sửa nghĩa?");
            System.out.println("1. Từ.");
            System.out.println("2. Nghĩa.");
            choose = Integer.parseInt(sc.nextLine());
            continueRepair(choose);
        }
    }
    
    // nhap tu vao File.
    public static void showWrite() {
        System.out.println("Bạn có muốn nhập từ vào Từ Điển không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1)
            insertWordIntoFile();
    }
    
    // viet tu vao File.
    public static void insertWordIntoFile() {
        Scanner sc = new Scanner(System.in);
        String word, mean;
        System.out.print("Nhập số từ bạn muốn nhập: ");
        int i = Integer.parseInt(sc.nextLine());
        while(i != 0) {
            System.out.print("Nhập từ: ");
            word = sc.nextLine();
            System.out.print("Nhập nghĩa: ");
            mean = sc.nextLine();
            write(new Dictionary(word.toLowerCase(), mean.toLowerCase()), f);
            i--;
        }
        
        System.out.println("Bạn có muốn nhập thêm từ không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        i = Integer.parseInt(sc.nextLine());
        if(i == 1 )
            insertWordIntoFile();
    }
    
     // xoa tu.
    public static void showDeleteWord(boolean first) {
        if(first == true)
            System.out.println("Ban có muốn xóa từ khỏi từ điển không?");
        else
            System.out.println("Ban có muốn tiếp tục xóa từ khỏi từ điển không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Nhập những từ bạn muốn xóa khỏi từ điển:");
            String listDeleteWord = sc.nextLine();
            StringTokenizer s = new StringTokenizer(listDeleteWord , ", ");
            while(s.hasMoreTokens()) {
                deleteWord(s.nextToken().toLowerCase(), f);
            }
            showDeleteWord(false);
        }
    }
    
     // doc toan bo File.
    public static void showRead() {
        System.out.println("Bạn có muốn xem toàn bộ từ có trong File");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            read_All(f);
        }
    }
    
}
