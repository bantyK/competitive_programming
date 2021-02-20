class SegmentTree2D {
	
	class NumMatrix {
		class SegmentTreeNode {
			int row1, row2, col1, col2;
			int sum;
			SegmentTreeNode c1, c2, c3, c4;

			SegmentTreeNode(int r1, int c1, int r2,int c2) {
				row1 = r1;
				col1 = c1;
				row2 = r2;
				col2 = c2;
				sum = 0;
			}
		}

		SegmentTreeNode root;

		public NumMatrix(int[][] matrix) {
			if(matrix.length == 0 || matrix[0].length) {
				return;
			}

			root = buildSegmentTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
		}

		private SegmentTreeNode buildSegmentTree(int[][] matrix, int row1, int col1, int row2, int col2) {
			if(row2 < row1 || col2 < col1) {
				return null;
			}

			SegmentTreeNode root = new SegmentTreeNode(row1, col1, row2, col2);
			if(row1 == row2 && col1 == col2) {
				root.sum = matrix[row1][col1];
				return root;
			}
		}
	}

	public static void main(String[] args) {
		
	}


}