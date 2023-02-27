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

    /*    // use for store the n and int[][]
    public static class InputData {
        public int n;
        public int[][] houses;

        public InputData(int n, int[][] houses) {
            this.n = n;
            this.houses = houses;
        }
    }

    //use for read data from file.txt
    public static InputData ReadInputData(String inputFile) {
        try {
            File newFile = new File(inputFile);
            Scanner scanner = new Scanner(newFile);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] houses = new int[m][3];
            for (int i = 0; i < m; i++) {
                houses[i][0] = scanner.nextInt();
                houses[i][1] = scanner.nextInt();
                houses[i][2] = i;
            }
            scanner.close();
            return new InputData(n, houses);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
    }*/


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

/*        Scanner scanner = new Scanner(System.in);

        System.out.println("please input the data");
        // read the first line with n and m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arrs = new int[m][3];
        // read the m lines with start and end dates
        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            arrs[i][0] = start;
            arrs[i][1] = end;
            arrs[i][2] = i + 1;

        }
        for (int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
        ArrayList<Integer> list = HousePainter.HousePainterRun1(n, arrs);
        System.out.println("the output is:");
        //System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
            scanner.close();
        }*/



