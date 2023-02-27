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
                    paintedHouse.add(index + 1);
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
        //Maintain an empty pq to store all available houses, for STRAT2, sort in descending order of houses[][0]
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);
        int point = 0; //Pointer, used to traverse houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //Put all startday <= currentday houses into avaiHouse
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //Determine whether the endday at the top of the heap is greater than or equal to day, and if not, poll it.
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //Take out is the result
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2]);
            }
        }
        return paintedHouse;
    }

    //paint the house that is available for the shortest duration
    public static ArrayList<Integer> HousePainterRun3(int maxWorkDays, int[][] houses) {
        //Maintain an empty pq to store all available houses, for STRAT3, sort in ascending order of houses[][1]-houses[][0]
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>(Comparator.comparingInt(o -> (o[1] - o[0])));
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);
        int point = 0; //Pointer, used to traverse houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //Put all startday <= currentday houses into avaiHouse
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //Determine whether the endday at the top of the heap is greater than or equal to day, and if not, poll it.
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //Take out is the result
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2]);
            }
        }
        return paintedHouse;
    }

    //paint the house that will stop being available the earliest
    public static ArrayList<Integer> HousePainterRun4(int maxWorkDays, int[][] houses) {
        //Maintain an empty pq to store all available houses, for STRAT3, sort in ascending order of houses[][1]
        PriorityQueue<int[]> avaiHouse = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        ArrayList<Integer> paintedHouse = new ArrayList<>(houses.length);   //维护一个Arraylist用来储存house的index，也就是output
        int point = 0; //Pointer, used to traverse houses
        for (int day = 1; day <= maxWorkDays; day++) {
            if (point == houses.length && avaiHouse.isEmpty()) {
                break;
            }
            //Put all startday <= currentday houses into avaiHouse
            while (point < houses.length && houses[point][0] <= day) {
                avaiHouse.offer(houses[point]);
                point++;
            }

            //Determine whether the endday at the top of the heap is greater than or equal to day, and if not, poll it.
            while (!avaiHouse.isEmpty() && avaiHouse.peek()[1] < day) {
                avaiHouse.poll();
            }
            //Take out is the result
            if (!avaiHouse.isEmpty()) {
                paintedHouse.add(avaiHouse.poll()[2]);
            }
        }
        return paintedHouse;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      /*  System.out.println("Please enter the absolute path of the data");
        String s = scan.nextLine();
        //InputData newData = HousePainter.ReadInputData("src/input.txt");
        InputData newData = HousePainter.ReadInputData(s);
        assert newData != null;*/

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
        ArrayList<Integer> list = new ArrayList<>();
        switch (args[0]) {
            case "1":
                list = HousePainter.HousePainterRun1(n, arrs);
                break;
            case "2":
                list = HousePainter.HousePainterRun2(n, arrs);
                break;
            case "3":
                list = HousePainter.HousePainterRun3(n, arrs);
                break;
            case "4":
                list = HousePainter.HousePainterRun4(n, arrs);
                break;
            default:
                System.out.println("your input is illegal");
                break;
        }
        // ArrayList<Integer> list = HousePainter.HousePainterRun1(newData.n, newData.houses);
        System.out.println("the output is:");
        //System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }

        }

    }

}