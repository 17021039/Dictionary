/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudien2;
import java.io.*;
import java.util.*;

class Dictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    String word;
    String mean;
 
    public Dictionary(String word, String mean) {
        super();
        this.word = word;
        this.mean = mean;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
 
    public String toString() {
        return word + " : " + mean;
    }
}

public class Logic {
    public static final File f = new File("tuDien.dat");

     // kiem tra file co object hay khong.
    public static boolean hasObject(File f) {
        // thu doc xem co object nao chua.
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(fi);
            if (inStream.readObject() == null) {
                check = false;
            }
            inStream.close();
 
        } catch (FileNotFoundException e) {
            check = false;
        } catch (IOException e) {
            check = false;
        } catch (ClassNotFoundException e) {
            check = false;
            e.printStackTrace();
        }
        return check;
    }
    
     // kiem tra xem  co tu do trong File chua.
    public static boolean sameWord(String word, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        boolean check = false;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            Dictionary s;
            while(true) {
                s = (Dictionary) inStream.readObject();
                if( s.getWord() == word) {
                    check = true;
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return check;
    }
    
     // viet tu vao File.
    public static void write(Dictionary s, File f) {
        try {
            FileOutputStream fo;
            ObjectOutputStream oStream = null;
 
            // neu file chu ton tai thi tao file va ghi binh thuong.
            if (!f.exists()) {
                fo = new FileOutputStream(f);
                oStream = new ObjectOutputStream(fo);
            } else { // neu file ton tai.
 
                // neu chua co thi ghi binh thuong.
                if (!hasObject(f)) {
                    fo = new FileOutputStream(f);
                    oStream = new ObjectOutputStream(fo);
                } else { // neu co roi thi ghi them vao.
 
                    fo = new FileOutputStream(f, true);
 
                    oStream = new ObjectOutputStream(fo) {
                        protected void writeStreamHeader() throws IOException {
                            reset();
                        }
                    };
                }
            }
 
            if(sameWord(s.getWord(), f) == false)
                oStream.writeObject(s);
            oStream.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     // doc toan bo tu trong File.
    public static void read(File f) {
        FileInputStream fis;
        ObjectInputStream inStream = null;
        try {
            fis = new FileInputStream(f);
            inStream = new ObjectInputStream(fis);
            Object s;
            int i = 0;
            while (true) {
                s = inStream.readObject();
                System.out.println(++i + " : " + s.toString());
            }
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
        }
    } 
    
     // tim tu trong File.
    public static String findWord(String word, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        String mean = null;
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord() == word) {
                    mean = s.getMean();
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return mean;
    }
    
     // tim nghia trong File.
    public static ArrayList<String> findMean(String mean, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        ArrayList<String> word = new ArrayList();
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getMean() == mean) {
                    word.add(s.getWord());
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return word;
    }
    
     // in ra tu, nghia vua tim.
    public static void showFind(int choose) {
        Scanner sc = new Scanner(System.in);
        String find;
        if(choose == 1) {
            System.out.print("Nhập từ bạn muốn tìm: ");
            find = sc.nextLine();
            System.out.println("nghĩa của từ " + find + " là: " + findWord(find, f));
        } else {
            System.out.print("Nhập nghĩa bạn muốn tìm: ");
            find = sc.nextLine();
            ArrayList<String> word = findMean(find, f);
            if(word.size() == 1) {
                System.out.println("Từ có nghĩa " + find + " là: " + word.get(0));
            } else {
                System.out.print("Những từ có nghĩa " + find + " là: ");
                for(int i = 0; i < word.size()-1; i++) {
                    System.out.print(word.get(i) + ", ");
                }
                System.out.println(word.get(word.size()-1) + ".");
            }
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
            showFind(choose);
        }
    }
    
     // sua tu trong File.
    public static void repairWord(String incorrectWord, String correctWord, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord() == incorrectWord) {
                    s.setWord(correctWord);
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    
     // sua nghia trong File.
    public static void repairMean(String Word, String correctMean, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord() == Word) {
                    s.setMean(correctMean);
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    
     // tiep tuc sua neu muon sua tiep.
    public static void continueRepair(int choose) {
        Scanner sc = new Scanner(System.in);
        String incorrect,correct;
        if(choose ==1 ) {
            System.out.print("Nhập từ sai: ");
            incorrect = sc.nextLine();
            System.out.print("Nhập từ đúng: ");
            correct = sc.nextLine();
            repairWord(incorrect, correct, f);
        } else {
            System.out.print("Nhập từ có nghĩa sai: ");
            incorrect = sc.nextLine();
            System.out.print("Nhập nghĩa đúng: ");
            correct = sc.nextLine();
            repairMean(incorrect, correct, f);
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
    
     //xoa tu trong File.
    public static void deleteWord(String Word, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord() == Word) {
                    
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
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
            write(new Dictionary(word, mean), f);
            i--;
        }
        
        System.out.println("Bạn có muốn nhập thêm từ không?");
        System.out.println("1. Có.");
        System.out.println("2. Không.");
        i = Integer.parseInt(sc.nextLine());
        if(i == 1 )
            insertWordIntoFile();
    }
}
     
    
   
