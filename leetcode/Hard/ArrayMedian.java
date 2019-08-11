public class ArrayMedian {
	public static void main(String[] args) {
		ArrayMedian arrayMedian = new ArrayMedian();
		int[] nums1 = new int[]{1,2};
		int[] nums2 = new int[]{3,4};
		double median = arrayMedian.findMedianForSortedArrays(nums1, nums2);
		System.out.println(median);
	}

	private double findMedianForSortedArrays(int[] nums1, int[] nums2) {
		int[] merged = getMergedArray(nums1, nums2);
		int len = merged.length;
		double median;
		if(len % 2 == 0) {
			//even length array
			int mid = (len - 1)/2;
			median = (merged[mid] + merged[mid + 1])/2.0;
		} else {
			// odd length array
			int mid = len / 2;
			median = merged[mid];
		}

		return median;		
	}

	private int[] getMergedArray(int[]nums1, int[] nums2) {
		int left1 = 0;
		int left2 = 0;
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] merged = new int[len1 + len2];
		int k = 0;

		while((left1 < len1) && (left2 < len2)) {
			if(nums1[left1] <= nums2[left2]) {
				merged[k++] = nums1[left1++];
			} else {
				merged[k++] = nums2[left2++];
			}
		}
		
		while(left1 < len1){
			merged[k++] = nums1[left1++];
		} 

		while(left2 < len2){
			merged[k++] = nums2[left2++];
		}

		return merged;
	} 
	
}