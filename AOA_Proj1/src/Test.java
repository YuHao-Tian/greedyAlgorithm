import java.io.*;
import java.util.*;


public class Test {
    //Randomly generate the startdday and endday of the house and store them in a two-dimensional array
    public static int[][] GenerateInputData(int houseNumber, int endDay){
        int[][] res = new int[houseNumber][2];
        Random random = new Random();
        for (int i = 0; i < houseNumber; i++) {
            res[i][0] = random.nextInt(endDay)+1;
            res[i][1] = res[i][0]+random.nextInt(endDay-res[i][0]+1);
        }
        Arrays.sort(res, (pair1, pair2) -> {
            if (pair1[0] != pair2[0]) {
                return pair1[0] - pair2[0];
            } else {
                return pair1[1] - pair2[1];
            }
        });
        return res;
    }


    public static void main(String[] args) {
        int[][] data = Test.GenerateInputData(10,10);
        String filename = "src/input.txt";
        int n = 10;
        int m = data.length;
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(n + " "+ m);
            bufferedWriter.newLine();
            // write into input.txt中
            for (int[] datum : data) {
                int num1 = datum[0];
                int num2 = datum[1];
                bufferedWriter.write(num1 + " " + num2);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("success！");

        } catch (IOException e) {
            System.out.println("fail to write into file!");
            e.printStackTrace();
        }

        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println(n+" "+m);
    }
}





