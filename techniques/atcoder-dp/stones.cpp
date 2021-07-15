// Problem: https://atcoder.jp/contests/dp/tasks/dp_k
#include <bits/stdc++.h>
#define ll long long
#define mod 1000000007
#define inf (1LL<<60)
#define fast_io ios_base::sync_with_stdio(false);cin.tie(NULL)
#define loop(i,s,e) for(int i = s; i < e; ++i)
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    vector<int> stones(n);
    
    loop(i, 0, n) {
        cin >> stones[i];
    }

    int dp[k];

    /*
        dp[i] = 0 meaning i is the loosing state (no matter how you play from here, you will always loose)
        dp[i] = 1 meaning i is the winning state (if you start from this state, you will win)

        The dp table records the win or loose state of player 1 
    */

    // if there are no stones to pick from than player 1 cannot make his move and this means that he looses
    dp[0] = 0; 

    for(int i = 1; i <= k; i++) {
        dp[i] = 0; 
        for(int x : stones) {
            /*
                If I choose a stone and the next state is a loosing state that means this state (ith state) 
                is a winning state for player 1
            */
            if(x <= i && dp[i - x] == 0) {
                dp[i] = 1;
                break; // If we find a state where first player is winning, than no need to look further
            }
        }
    }

    if(dp[k] == 1) {
        cout << "First";
    } else {
        cout << "Second";
    }
    cout << "\n";
    return 0;
}