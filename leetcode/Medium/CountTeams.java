// 1395 https://leetcode.com/problems/count-number-of-teams/
public class CountTeams {
    public static void main(String[] args) {
        CountTeams obj = new CountTeams();
        System.out.println(obj.numTeams(new int[]{2, 5, 3, 4, 1}) == 3);
        System.out.println(obj.numTeams(new int[]{2, 1, 3}) == 0);
        System.out.println(obj.numTeams(new int[]{1, 2, 3}) == 1);
        System.out.println(obj.numTeams(new int[]{1, 2, 3, 4}) == 4);
        System.out.println(obj.numTeams(new int[]{3, 2, 1}) == 1);
    }

    //O(n * 2) solution
    public int numTeams(int[] rating) {
        if (rating.length < 3) return 0;
        int numTeams = 0;

        for (int j = 0; j < rating.length - 1; j++) {
            // for the condition arr[i] > arr[j] > arr[k]
            int i = 0;
            int largerRatingCount = 0;
            while (i < j) {
                if (rating[i] > rating[j]) {
                    largerRatingCount++;
                }
                i++;
            }
            int k = j + 1;
            int smallerRatingCount = 0;
            while (k < rating.length) {
                if (rating[k] < rating[j]) {
                    smallerRatingCount++;
                }
                k++;
            }

            numTeams += largerRatingCount * smallerRatingCount;


            // for the condition arr[i] < arr[j] < arr[k]
            smallerRatingCount = 0;
            i = 0;
            while (i < j) {
                if (rating[i] < rating[j]) {
                    smallerRatingCount++;
                }
                i++;
            }
            k = j + 1;
            largerRatingCount = 0;
            while (k < rating.length) {
                if (rating[j] < rating[k]) {
                    largerRatingCount++;
                }
                k++;
            }
            numTeams += largerRatingCount * smallerRatingCount;
        }

        return numTeams;
    }


    // O(n * 3) solution
    public int numTeams2(int[] rating) {
        if (rating.length < 3) return 0;
        int numTeams = 0;

        for (int i = 0; i <= rating.length - 3; i++) {
            for (int j = i + 1; j <= rating.length - 2; j++) {
                int k = j + 1;
                if (rating[i] > rating[j]) {
                    while (k < rating.length) {
                        if (rating[k] < rating[j]) {
                            numTeams++;
                        }
                        k++;
                    }
                } else {
                    while (k < rating.length) {
                        if (rating[k] > rating[j]) {
                            numTeams++;
                        }
                        k++;
                    }
                }
            }
        }
        return numTeams;
    }
}
