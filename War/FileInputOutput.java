import java.io.*;
import java.util.Scanner;

public class FileInputOutput {
 public static void main(String[] args) throws IOException {
 File infile = new File("input");
 Scanner input = new Scanner(infile);
 FileWriter outfile = new FileWriter("output");
 PrintWriter output = new PrintWriter(outfile);
 double x = input.nextDouble();
 output.println(x);
 output.close();
 }
}