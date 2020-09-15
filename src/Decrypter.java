//Programmer: Alex Morales
// Program Name: Decrypter/Encrypter.java
// Purpose: The purpose of this first assignment is to get you more familiar with writing basic Java programs and pushing your source code to GitHub.
//Date Written: 09/14/2020

public class Decrypter {
    public String decrypt(String args) {

        int data = Integer.parseInt(args);
        int[] temp = {0, 0, 0, 0};
          // REFER TO ENCRYPTER NOTE FOR CLARIFICATION
        for (int i = 0; i < 4; i++) {
            temp[3 - i] = data % 10;
            
            data = data / 10;
        }

        temp = step1(temp);


        temp = step2(temp);


        data = temp[0] * 1000 + temp [1] * 100 + temp[2] * 10 + temp[3];
        
        args = Integer.toString(data);

     
        while (args.length()<4) {
            args = "0" + args;
        }
        return args;

    }
    public static int[] step1(int[] temp){
        int passover;
       
        passover = temp[0];
        
        temp[0] = temp[2];
        
        temp[2] = passover;
      
        passover = temp[1];
       
        temp[1]  = temp[3];
        
        temp[3] = passover;
        return temp;
    }
    public static int[] step2(int[] args){
        for(int i = 0; i < 4; i++){
            args[i] = (args[i] + 3) % 10;
        }
        return args;
    }
}
