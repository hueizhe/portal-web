


public class Solution {



    public static String solution(int a, int b, int c, int d, int e, int f) {

        int Array [] = { a, b, c, d, e, f};

        quickSort(Array,0 , Array.length-1);



        for(int i=0; i<Array.length; i++){
            System.out.println(i);
        }



        return null;
    }




    private static void quickSort(int[] a, int begin, int end) {
        //
        int tbegin = begin, tend = end;
        // 将第一个值作为快排序的分界值
        int pivot = a[begin];
        while (tbegin < tend) {
            // 如果两个游标没有交集，或者后面一直大于或等于分界值就一直向前移动
            while (tbegin < tend && a[tend] >= pivot) {
                --tend;
            }
            a[tbegin] = a[tend];
            // 如果两个游标没有交集，或者前面是小于或等于分界值，就一直向后头移动
            while (tbegin < tend && a[tbegin] <= pivot) {
                ++tbegin;
            }
            a[tend] = a[tbegin];

        }
        // 将临界值赋值给游标的交集的地方
        a[tbegin] = pivot;
        if (begin < tend) {
            // 递归排序游标的左边
            quickSort(a, begin, tend - 1);
        }
        if (tbegin < end) {
            // 递归排序游标的右边
            quickSort(a, tbegin + 1, end);
        }

    }
}
