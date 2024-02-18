/* Edward Rosales
 * Dr. Andrew Steinberg
 * COP3503 Fall 2023
 * Programming Assignment 4
 */

public class SequenceAlignment {
	
    private String word1;
    private String word2;
    private int[][] dpTable;
    private String alignment;
    private int delta, sameSymbolCost = 0, vowelDifferentCost = 1,constantDifferentCost = 1, vowelConstantCost = 3;

    // Constructor
    public SequenceAlignment(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        dpTable = new int[word1.length() + 1][word2.length() + 1];
        
        //delta = 2 for test cases
        computeAlignment(delta);
    }

    // Method to compute alignment
    public void computeAlignment(int n) {
        this.delta = n; // Set delta value for mismatches

        // Iterating through the dynamic programming table
        for (int i = 0; i <= word1.length(); i++) { // Loop through word1
            for (int j = 0; j <= word2.length(); j++) { // Loop through word2
                if (i == 0) {
                    // If word1 is empty, fill the row with cost based on word2 length multiplied by delta
                    dpTable[i][j] = j * delta;
                } else if (j == 0) {
                    // If word2 is empty, fill the column with cost based on word1 length multiplied by delta
                    dpTable[i][j] = i * delta;
                } else {
                    // Calculate the cost based on whether characters match or not
                    int cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? sameSymbolCost : getMismatchCost(word1.charAt(i - 1), word2.charAt(j - 1));
                    // Fill the cell with the minimum cost based on three possible operations
                    dpTable[i][j] = Math.min(dpTable[i - 1][j - 1] + cost, Math.min(dpTable[i - 1][j] + delta, dpTable[i][j - 1] + delta));
                }
            }
        }

        // Reconstructing the alignment strings using the filled dpTable
        StringBuilder alignSeq1 = new StringBuilder();
        StringBuilder alignSeq2 = new StringBuilder();
        int i = word1.length();
        int j = word2.length();

        while (i > 0 || j > 0) {
            // If characters at the current positions in word1 and word2 match
            if (i > 0 && j > 0 && word1.charAt(i - 1) == word2.charAt(j - 1)) {
                // Add matching characters to alignment sequences
                alignSeq1.insert(0, word1.charAt(i - 1));
                alignSeq2.insert(0, word2.charAt(j - 1));
                i--; // Move diagonally up and left in the dpTable
                j--;
            } 
            // If the cost in dpTable comes from a mismatch with the same cost
            else if (i > 0 && j > 0 && dpTable[i][j] == dpTable[i - 1][j - 1] + getMismatchCost(word1.charAt(i - 1), word2.charAt(j - 1))) {
                // Add mismatching characters to alignment sequences
                alignSeq1.insert(0, word1.charAt(i - 1));
                alignSeq2.insert(0, word2.charAt(j - 1));
                i--; // Move diagonally up and left in the dpTable
                j--;
            } 
            // If the cost comes from a gap in word1
            else if (i > 0 && dpTable[i][j] == dpTable[i - 1][j] + delta) {
                // Insert a gap in alignSeq1 to represent a gap in word1
                alignSeq1.insert(0, word1.charAt(i - 1));
                alignSeq2.insert(0, '-');
                i--; // Move up in the dpTable
            } 
            // If the cost comes from a gap in word2
            else {
                // Insert a gap in alignSeq2 to represent a gap in word2
                alignSeq1.insert(0, '-');
                alignSeq2.insert(0, word2.charAt(j - 1));
                j--; // Move left in the dpTable
            }
        }


        // Combine the reconstructed alignment strings
        alignment = alignSeq1.toString() + " " + alignSeq2.toString();

        // Print the final alignment string 
        //System.out.println("toString: " + alignment);
    }



    // Helper method to calculate mismatch costs
    private int getMismatchCost(char a, char b) {
        if (isVowel(a) && isVowel(b)) {	//both are vowels, +1
            return vowelDifferentCost;
        } else if (!isVowel(a) && !isVowel(b)) { //both are constants +1
            return constantDifferentCost;
        } else {
            return vowelConstantCost;	//one is a vowel and one is a constant +3
        }
    }

    // method to check if a character is a vowel
    private boolean isVowel(char c) {
    	//finds the index of 'c' in "aeiou"
    	//if 'c' is in "aeiou" then it will be a value 0-4(true). else -1(false)
        return "aeiou".indexOf(c) != -1;
    }
    
    public String getAlignment() {
    	/*for(int i = 0; i < word1.length()+1; i++) {
    		for (int j = 0; j < word2.length()+1; j++) {
    			System.out.print(dpTable[i][j] + " ");
			
			}
    		System.out.println();
    	}*/
    	
        return alignment;
    }
    
}
