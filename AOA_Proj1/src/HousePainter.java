import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HousePainter {
    //paint the house that started being available the earliest.
    public static ArrayList<Integer> HousePainterRun1(int maxWorkDays, int[][] houses) {
        //O(n)
        int index = 0;
        ArrayList<Integer> paintedHouse = new ArrayList<>();//output
        for (int day = 1; day <= maxWorkDays; day++) {
            while (index < houses.length && day >= houses[index][0]) {
                if (day <= houses[index][1]) {
                    paintedHouse.add(index + 1);//这里+2方便看，后面删掉
                    index++;
                    break;
                } else {
                    index++;
                }
            }

        }
        return paintedHouse;
    }

    //paint the house that started being available the latest
    public static ArrayList<Integer> HousePainterRun2(int maxWorkDays, int[][] houses) {
        //维护一个空pq，用来存储所有available房子,对于STRAT2，以 houses[][0] 降序排序
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);   //维护一个Arraylist用来储存house的index，也就是output
        int point = 0; //指针，用来遍历houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //把所有startday <= currentday的house放进avaiHouse中去
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //判断堆顶的endday是否大于等于day，如果不是就poll掉。
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //如果是，取出即为结果
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2] + 1);//这里+2方便看，后面删掉
            }
        }
        return paintedHouse;
    }

    public static ArrayList<Integer> HousePainterRun3(int maxWorkDays, int[][] houses) {
        //维护一个空pq，用来存储所有available房子,对于STRAT3，以duration 升序排序
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>(Comparator.comparingInt(o -> (o[1] - o[0])));
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);   //维护一个Arraylist用来储存house的index，也就是output
        int point = 0; //指针，用来遍历houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //把所有startday <= currentday的house放进avaiHouse中去
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //判断堆顶的endday是否大于等于day，如果不是就poll掉。
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //如果是，取出即为结果
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2] + 1);//这里+2方便看，后面删掉
            }
        }
        return paintedHouse;
    }

    public static ArrayList<Integer> HousePainterRun4(int maxWorkDays, int[][] houses) {
        //维护一个空pq，用来存储所有available房子,对于STRAT4，以 houses[][1] 升序排序
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);   //维护一个Arraylist用来储存house的index，也就是output
        int point = 0; //指针，用来遍历houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //把所有startday <= currentday的house放进avaiHouse中去
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //判断堆顶的endday是否大于等于day，如果不是就poll掉。
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //如果是，取出即为结果
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2] + 1);//这里+2方便看，后面删掉
            }
        }
        return paintedHouse;
    }

      /*   public static int HousePainterRunOptimal(int n,int[][] arr) {

    }*/

    public static class InputData {
        public int n;
        public int[][] houses;

        public InputData(int n, int[][] houses) {
            this.n = n;
            this.houses = houses;
        }
    }

    //read data自用
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
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the absolute path of the data");
        String s = scan.nextLine();
        //InputData newData = HousePainter.ReadInputData("src/input.txt");
        InputData newData = HousePainter.ReadInputData(s);
        assert newData != null;
        ArrayList<Integer> list = HousePainter.HousePainterRun1(newData.n, newData.houses);
        System.out.println("the output is:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
        }
    }

}

