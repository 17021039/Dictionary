/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudien2;
import java.io.*;
import java.nio.file.*;
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
    public static final File f = new File("TuDien.dat");
    public static final File t = new File("E:\\Temporary.dat");
    public static final Path source = Paths.get(f.getPath());
    public static final Path target = Paths.get(t.getPath());
    

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
                if( s.getWord().equals(word)) {
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
        FileOutputStream fos = null;
        ObjectOutputStream oStream = null;
        try {
            // neu file chu ton tai thi tao file va ghi binh thuong.
            if (!f.exists()) {
                f.createNewFile();
                fos = new FileOutputStream(f);
                oStream = new ObjectOutputStream(fos);
            } else { // neu file ton tai.
 
                // neu chua co thi ghi binh thuong.
                if (!hasObject(f)) {
                    fos = new FileOutputStream(f);
                    oStream = new ObjectOutputStream(fos);
                } else { // neu co roi thi ghi them vao.
 
                    fos = new FileOutputStream(f, true);
 
                    oStream = new ObjectOutputStream(fos) {
                        protected void writeStreamHeader() throws IOException {
                            reset();
                        }
                    };
                }
            }
 
            if(sameWord(s.getWord(), f) == false)
                oStream.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oStream != null) {
                try {
                    oStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
        }
    }
    
     // xoa het du lieu cu ghi lai tu dau.
    public static void writeBegin(Dictionary s, File f) {
        try {
            if(!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oStream = new ObjectOutputStream(fos);
            oStream.writeObject(s);
            oStream.close();
        } catch (IOException e) {
        }
    }
    
     // doc toan bo tu trong File.
    public static void read_All(File f) {
        FileInputStream fis = null;
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
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
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
                if(s.getWord().equals(word)) {
                    mean = s.getMean();
                    break;
                }
            }
            inStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        
        if(mean == null)
            return "NOT FOUND";
        else
            return mean + ".";
    }
    
     // tim nghia trong File.
    public static String findMean(String mean, File f) {
        FileInputStream fi;
        ObjectInputStream inStream = null;
        String word = null;
        Dictionary s;
        try {
            fi = new FileInputStream(f);
            inStream = new ObjectInputStream(fi);
            while(true) {
                s = (Dictionary) inStream.readObject();
                StringTokenizer st = new StringTokenizer(s.getMean(), ",");
                while(st.hasMoreTokens()) {
                    String str = st.nextToken();
                    if(str.startsWith(" "))
                        str = str.substring(1);
                    if(str.equals(mean)) {
                        word += s.getWord() + ", ";
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        
        if(word == null)
            return "NOT FOUND";
        else
            return word.substring(4,word.length()-2) + ".";
    }
    
     // sua tu trong File.
    public static void repairWord(String incorrectWord, String correctWord, File f) {
        FileInputStream fis = null;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
            fis = new FileInputStream(target.toFile());
            inStream = new ObjectInputStream(fis);
            
            s = (Dictionary) inStream.readObject();
            if(s.getWord().equals(incorrectWord)) {
                s.setWord(correctWord);
            }
            writeBegin(s, f);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord().equals(incorrectWord)) {
                    s.setWord(correctWord);
                }
                write(s, f);
            } 
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.delete(target);
            } catch(IOException e) {
            }
        }
    }
    
     // sua nghia trong File.
    public static void repairMean(String Word, String correctMean, File f) {
        FileInputStream fis = null;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
            fis = new FileInputStream(target.toFile());
            inStream = new ObjectInputStream(fis);
            
            s = (Dictionary) inStream.readObject();
            if(s.getWord().equals(Word)) {
                s.setMean(correctMean);
            }
            writeBegin(s, f);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(s.getWord().equals(Word)) {
                    s.setMean(correctMean);
                }
                write(s, f);
            } 
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.delete(target);
            } catch(IOException e) {
            }
        }
    }
    
    
    
     //xoa tu trong File.
    public static void deleteWord(String Word, File f) {
        FileInputStream fis = null;
        ObjectInputStream inStream = null;
        Dictionary s;
        try {
            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
            fis = new FileInputStream(target.toFile());
            inStream = new ObjectInputStream(fis);
            
            s = (Dictionary) inStream.readObject();
            if(s.getWord().equals(Word))
                s = (Dictionary) inStream.readObject();
            writeBegin(s, f);
            while(true) {
                s = (Dictionary) inStream.readObject();
                if(!s.getWord().equals(Word)) {
                    write(s, f);
                }
                
            } 
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.delete(target);
            } catch(IOException e) {
            }
        }
    }
    
     
}
     
    
   
