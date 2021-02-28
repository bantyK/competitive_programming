// 1774 https://leetcode.com/problems/closest-dessert-cost/
class ClosestDesertCost {
    int bestDiff;
    int closestCost;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        bestDiff = Integer.MAX_VALUE;
        closestCost = 0;

        for (int i = 0; i < baseCosts.length; i++) {
            helper(toppingCosts, 0, target, baseCosts[i]);
        }

        return closestCost;
    }

    private void helper(int[] toppingCosts, int toppingIndex, int target, int currentCost) {

        if (toppingIndex == toppingCosts.length) {
            int diff = Math.abs(target - currentCost);
            if (diff <= bestDiff) {
                if (diff < bestDiff) {
                    closestCost = currentCost;
                    bestDiff = diff;
                } else {
                    if (currentCost < closestCost) {
                        closestCost = currentCost;
                    }
                }
            }
            return;
        }

        helper(toppingCosts, toppingIndex + 1, target, currentCost);
        helper(toppingCosts, toppingIndex + 1, target, currentCost + toppingCosts[toppingIndex]);
        helper(toppingCosts, toppingIndex + 1, target, currentCost + (2 * toppingCosts[toppingIndex]));
    }
}
