//968 https://leetcode.com/problems/binary-tree-cameras/
public class BinaryTreeCameras {
	private int numCameras;
	
	public int minCameraCover(TreeNode root) {
		numCameras = 0;
		Node temp = dfs(root);
		if(!temp.beingMonitored) {
			numCameras++;
		}
		return numCameras;
	}
	
	/*
	    Two cases are possible for a node
		1. The node has a camera. If it has camera it is being monitored also.
	    2. The node is being monitored (by its parent or its child). In this case, we dont need a camera
		
		Going from bottom node ensure that we are following an optimised approach and using the minimum 
		number of cameras.
	*/
	private Node dfs(TreeNode root) {
		if(root == null) {
			return new Node(false, true);
		}
		
		Node left = dfs(root.left);
		Node right = dfs(root.right);
		
		boolean hasCamera = false;
		boolean beingMonitored = false;
		
		if(left.hasCamera || right.hasCamera) {
			// if any of the child of the current node has a camera, then this node is also monitored
			// we dont have to put a camera here.
			beingMonitored = true;
		}
		
		if(!left.beingMonitored || !right.beingMonitored) {
			// if any of the two children are not monitored, we have to put a camera at the root
			// because we are coming from bottom up, if we are at the root and at this point, if any of
			// the children are not monitored, then there is no other way to monitor the child other than
			// putting the camera at the root
			
			numCameras++;
			hasCamera = true;
			beingMonitored = true; // a node which has a camera is automatically monitored
		}
		
		return new Node(hasCamera, beingMonitored);
	}
	
	private static class Node {
		boolean hasCamera;
		boolean beingMonitored;
		
		public Node(boolean x, boolean y) {
			hasCamera = x;
			beingMonitored = y;
		}
	}
}