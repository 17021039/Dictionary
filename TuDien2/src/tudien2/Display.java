/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudien2;
import java.util.*;
import static tudien2.Logic.*;


public class Display {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
         // nhap tu vao File.
        System.out.println("Bạn có muốn nhập từ vào Từ Điển không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        int choose;
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1)
            insertWordIntoFile();
        
         // doc toan bo File.
        read(f);
        
         // sua tu, nghia trong File.
        System.out.println("Bạn có muốn sửa từ, nghĩa trong Từ Điển không?");
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
        
         // tim tu, nghia
        System.out.println("Bạn có muốn tìm từ(nghĩa)?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        choose = Integer.parseInt(sc.nextLine());
        if(choose == 1) {
            System.out.println("Bạn muốn tìm theo từ hay nghĩa?");
            System.out.println("1. Từ.");
            System.out.println("2. Nghĩa.");
            choose = Integer.parseInt(sc.nextLine());
            showFind(choose);
        }
        
    }
}
