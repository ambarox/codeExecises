import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] A = new int[]{1, 4, 2};
        int[] P = new int[]{10, 4, 5};
        System.out.println(solution(A, P, 200, 1));
    }

    public static boolean solution(int[] A, int[] P, int B, int E) {
        List<int[]> range = new ArrayList<>();

        // Identify the maximum range of the each and every cranes
        for (int i = 0; i < A.length; i++) {
            range.add(new int[]{A[i] - P[i], A[i] + P[i]});
        }

        // Arrange it using the maximum end to find the continuaty
        Collections.sort(range, (a, b) -> a[1] - b[1]);

        List<int[]> disjoint = new ArrayList<>();

        for (int i = 0; i < range.size(); i++) {

            int start = range.get(i)[0];
            int end = range.get(i)[1];
            while (i < range.size() - 1 && end >= range.get(i + 1)[0]) {
                start = Math.min(start, range.get(i + 1)[0]);
                end = range.get(i + 1)[1];
                i++;
            }

            disjoint.add(new int[]{start, end});
        }

        int lo = 0, hi = disjoint.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi + 1) / 2;
            int[] tmp = disjoint.get(mid);
            if (B >= tmp[0] && B <= tmp[1]) {
                if (E >= tmp[0] && E <= tmp[1]) {
                    return true;
                } else {
                    return false;
                }

            } else if (B >= tmp[0]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return false;
    }

}
