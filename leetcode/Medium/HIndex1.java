public class HIndex1 {
	public int hIndex(int[] citations) {
        	Arrays.sort(citations);
        
       		int low = 0;
        	int high = citations.length - 1;
       		int n = citations.length;
        
       		while(low <= high) {
            		int mid = (low + high) / 2;
            
           		 if(citations[mid] == n - mid) {
                		return citations[mid];
            		} else if(citations[mid] > n - mid) {
               			 high = mid - 1;
            		} else {
                		low = mid + 1;
            		}
        	}
        
        	return n - low;
    	}
}


