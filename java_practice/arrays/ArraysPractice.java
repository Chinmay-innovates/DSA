
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArraysPractice {

    int linearSearch(int nums[], int key) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                return i;
            }
        }
        return -1;
    }

    int binarySearch(int nums[], int key) {
        int st = 0, end = nums.length - 1;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (nums[mid] == key) {
                return mid;
            } else if (nums[mid] < key) {
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    int rotatedSortedArray(int nums[], int target) {
        int st = 0, end = nums.length - 1;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[st] <= nums[mid]) {
                if (target >= nums[st] && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    st = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[end]) {
                    st = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    int maxSubArraySum(int nums[]) {
        int[] prefix = new int[nums.length];
        int currSum, maxSum = Integer.MIN_VALUE;

        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                currSum = start == 0 ? prefix[end] : prefix[end] - prefix[start - 1];
                if (maxSum < currSum) {
                    maxSum = currSum;
                }
            }
        }

        System.out.println("Max Sum: " + maxSum);
        return maxSum;
    }

    int kadanes(int nums[]) {
        if (nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0], currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // currSum += nums[i];
            // if (currSum < 0) {
            //     currSum = 0;
            // }
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }

        System.out.println("Max Sum (Kadane's Algorithm): " + maxSum);
        return maxSum;
    }

    int trappedRainWater(int heights[]) {
        int n = heights.length;
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];

        leftMax[0] = heights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(heights[i], leftMax[i - 1]);
        }

        rightMax[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(heights[i], rightMax[i + 1]);
        }

        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            waterTrapped += waterLevel - heights[i];
        }

        System.out.println("Water Trapped: " + waterTrapped);
        return waterTrapped;
    }

    int trappedRainWater(int heights[], boolean optimizeSpace) {
        int n = heights.length;
        if (n < 3) {
            return 0;
        }
        if (!optimizeSpace) {
            return trappedRainWater(heights);
        }

        int left = 0, right = n - 1, leftMax = 0, rightMax = 0, waterTrapped = 0;

        //  heights = [4, 2, 0, 6, 3, 2, 5]
        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] > leftMax) {
                    leftMax = heights[left];
                } else {
                    waterTrapped += leftMax - heights[left];
                }
                left++;
            } else {
                if (heights[right] > rightMax) {
                    rightMax = heights[right];
                } else {
                    waterTrapped += rightMax - heights[right];
                }
                right--;
            }
        }

        System.out.println("Water Trapped (optimizeSpace): " + waterTrapped);
        return waterTrapped;
    }

    int bestBuySellStock(int prices[]) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int costPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price : prices) {
            costPrice = Math.min(costPrice, price);
            maxProfit = Math.max(maxProfit, price - costPrice);
        }
        System.out.println("Best profit: " + maxProfit);
        return maxProfit;
    }

    boolean containDuplicates(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    triplets.add(Arrays.asList(nums[left], nums[i], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        ArraysPractice obj = new ArraysPractice();
        int[] subArrSum = {1, -2, 6, -1, 3}, rotSortedArray = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(obj.binarySearch(new int[]{0, 3, 4, 6, 7, 8}, 8));
        System.out.println(obj.linearSearch(new int[]{3, 7, 4, 8, 6, 0}, 4));

        obj.kadanes(new int[]{-7, -6, -3, -2, -9});
        obj.maxSubArraySum(subArrSum);
        obj.kadanes(subArrSum);

        obj.trappedRainWater(new int[]{4, 2, 0, 6, 3, 2, 5}, true);
        obj.bestBuySellStock(new int[]{7, 1, 5, 3, 6, 4});

        System.out.println(obj.containDuplicates(new int[]{1, 2, 3, 1}));
        System.out.println(obj.rotatedSortedArray(rotSortedArray, 0));
        System.out.println(obj.rotatedSortedArray(rotSortedArray, 3));

        System.out.println(obj.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
