//Programmer: Alex Morales
// Program Name: Decrypter.java
// Purpose: The purpose of this first assignment is to get you more familiar with writing basic Java programs and pushing your source code to GitHub.
//Date Written: 09/14/2020

public class Application {

    public static void main(String[] args) {

        Encrypter myEncrypter = new Encrypter();
        
        String encryptedValue = myEncrypter.encrypt("4321");

        System.out.println(encryptedValue);


        Decrypter myDecrypter = new Decrypter();

        String decryptedValue = myDecrypter.decrypt("9810");

        System.out.println(decryptedValue);

    }
}


