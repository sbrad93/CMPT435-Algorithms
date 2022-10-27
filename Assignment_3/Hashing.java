// Referenced from https://www.labouseur.com/courses/algorithms/Hashing.java.html
public class Hashing {
   
    public final int HASH_TABLE_SIZE = 250;  

    public int makeHashCode(String str) {
        str = str.toUpperCase();
        int length = str.length();
        int letterTotal = 0;

        // Iterate over all letters in the string, totalling their ASCII values.
        for (int i = 0; i < length; i++) {            
            char thisLetter = str.charAt(i);            
            int thisValue = (int)thisLetter;
            letterTotal = letterTotal + thisValue;

            // Test: print the char and the hash.
            /* 
            System.out.print(" ["); 
            System.out.print(thisLetter); 
            System.out.print(thisValue); 
            System.out.print("] "); 
            // */
        }

        // Scale letterTotal to fit in HASH_TABLE_SIZE.
        int hashCode = (letterTotal * 1) % HASH_TABLE_SIZE;  // % is the "mod" operator

        return hashCode;
    }
}  