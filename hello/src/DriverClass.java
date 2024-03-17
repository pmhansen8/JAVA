/*
Patrick Hansen
 11/3/2023
 HW 5
 */




import java.util.Scanner;

public class DriverClass {
    public static void main(String args[]) {
        int numRows, numColumns;
        int index, start, end;
        char charAtIndex;
        int length;
        String subSequence;
        Scanner myScan = new Scanner(System.in);
        System.out.print("Enter how many rows and how many columns to load: ");
        numRows = myScan.nextInt();
        numColumns = myScan.nextInt();
        Code codeObject = new Code(numRows, numColumns);
        codeObject.loadCodeArray(numRows, numColumns);
        codeObject.printCodeArray(numRows, numColumns);
// __________________________________________
        System.out.print("\n\nTesting charAt: Enter your index [a number greater or equal to 0 and less or equal to ");
                System.out.print((numRows * numColumns - 1) + "]:");
        index = myScan.nextInt();
        charAtIndex = codeObject.charAt(index);
        System.out.println("The character located at index " + index + " is: " +
                charAtIndex);
// __________________________________________
        length = numRows * numColumns;
        System.out.println("\n\nTesting length: there are " + length + " characters.");
// __________________________________________
                System.out.print("\n\nTesting subSequence: Enter start and end indexes: ");
                        start = myScan.nextInt();
        end = myScan.nextInt();
        subSequence = codeObject.subSequence(start, end);
        System.out.println("The subsequuence is: " + subSequence);
// __________________________________________
        System.out.println("\nGoodbye!");
    }
}
//_____________________________________
class Code implements CharSequence {
    private int[][] codeArray;
    private int numRows, numColumns;

    public Code(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        codeArray = new int[numRows][numColumns];
    }

    public void loadCodeArray(int numRows, int numColumns) {
        Scanner myScan = new Scanner(System.in);
        int i, j;
        for (i = 0; i < numRows; i++) {
            System.out.print("Enter Row " + (i + 1) + ": ");
            for (j = 0; j < numColumns; j++) {
                codeArray[i][j] = myScan.nextInt();
            }
        }
    }

    public void printCodeArray(int numRows, int numColumns) {
        int i, j;
        System.out.println("\n_____________\n");
        for (i = 0; i < numRows; i++) {
            for (j = 0; j < numColumns; j++) {
                System.out.print(codeArray[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    // ______________THE CODE ABOVE IS TO REMAIN UNCHANGED______________________
    @Override
    public char charAt(int index) {
        // first check if the provided index is within the valid range
        if (index < 0 || index >= numRows * numColumns) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        // Calculate the row and column of the element in codeArray
        int row = index / numColumns;
        int col = index % numColumns;

        // Get the integer value from codeArray and cast it to a character
        return (char) codeArray[row][col];
    }

    @Override
    public int length() {
        return numRows*numColumns;
    }

    @Override
    public String subSequence(int start, int end) {
        // Check if the provided start and end indexes are within the valid range
        if (start < 0 || end < start || end >= numRows * numColumns) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }

        StringBuilder subSeq = new StringBuilder(end - start + 1);

        // Extract the characters corresponding to the integers from start to end
        for (int i = start; i <= end; i++) {
            int row = i / numColumns;
            int col = i % numColumns;
            subSeq.append((char) codeArray[row][col]);
        }

        return subSeq.toString();
    }

}