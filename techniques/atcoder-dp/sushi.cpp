// Problem : https://atcoder.jp/contests/dp/tasks/dp_j
#include <bits/stdc++.h>
#define ll long long
#define mod 1000000007
#define inf (1LL<<60)
#define fast_io ios_base::sync_with_stdio(false);cin.tie(NULL)
#define loop(i,s,e) for(int i = s; i < e; ++i)
using namespace std;
const int N = 301;
/*
    We only need 3-D dp because we know that sum of all counts has to be n.
    Hence the 4th dimension can be calculated as follows
    a + b + c + d = n (n is constant here)
    d = n - (a + b + c)

    so if have calculated a, b and c then d's value can automatically be derived

    dp[a][b][b] => expected number of operations to reach the state (a, b, c)
*/
double dp[N][N][N];

int main() {
    fast_io;
    int n;
    cin >> n;
    int cnt[4] = {0}; //count of 0 sushi, 1 sushi, 2 sushi and 3 suhsi
    /*
        We dont care which dish contains how many sushis, all we care is that how many sushi are there
        i.e., how many dishes are there with 0 sushi, how many dishes have 1 sushi, similarly 2 and 3 sushis

        This info is maintained in the cnt array. cnt[0] = count of sushis with 0 sushi, similarly for cnt[1], cnt[2] & cnt[3]
    */
    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;
        cnt[x]++;
    }

    
    /*
        Dp transition:
        
        If I rolled a dice and got a number which has 3 sushi pieces (and If I have atleast one dish with 3 sushi pieces), 
        in this case, the count of dishes with 3 sushi pieces will decrease by 1 count of dishes with 2 sushi pieces
        increase by 1 and count with 1 sushi piece will remain the same
        
        This can be represented in DP state like this

        x -> count of dishes with 3 sushi pieces, y -> dishes with 2 pieces, 1 -> dishes with 1 piece, 0 -> not represented in dp state
        
        p3 -> probability to pick a dish with 3 sushi pieces
        p2 -> probability to pick a dish with 2 sushi pieces
        p1 -> probability to pick a dish with 1 sushi pieces
        
        1 is added because I rolled the dice once to get either p0, p1, p2 or p3
        
        dp[x][y][z] = 1 + (p3 * dp[x - 1][y + 1][x]) + (p2 * dp[x][y - 1][x + 1]) + (p1 * dp[x][y][z-1]) + (p0 * dp[x][y][z]);
        
        dp[x][y][z] - (p0 * dp[x][y][z]) = 1 + (p3 * dp[x - 1][y + 1][x]) + (p2 * dp[x][y - 1][x + 1]) + (p1 * dp[x][y][z-1]);

        (1 - p0) * dp[x][y][z] = 1 + (p3 * dp[x - 1][y + 1][x]) + (p2 * dp[x][y - 1][x + 1]) + (p1 * dp[x][y][z-1]);

        dp[x][y][z] = (1 + (p3 * dp[x - 1][y + 1][x]) + (p2 * dp[x][y - 1][x + 1]) + (p1 * dp[x][y][z-1])) / (1 - p0);

    */

    dp[0][0][0] = 0; // base case

    for(int three = 0; three <= n; three++) {
        
        for(int two = 0; two <= n; two++) {
        
            for(int one = 0; one <= n; one++) {
        
                int zero = n - three - two - one;

                if(zero == n) {
                    // this means x, y, and z is zero, which is our base case, hence no need to go ahead
                    continue;
                }
                
                if(one + two + three > n) {
                    // invalid state, might occur because we are iterating over all possible values of one, two and three
                    continue;
                }

                double val = 1; // I have rolled the dice
                
                // pick 3
                if(three > 0) {
                    val += ((1.0 * three) / n) * dp[three - 1][two + 1][one];
                }
                
                // pick 2
                if(two > 0) {
                    val += ((1.0 * two) / n) * dp[three][two - 1][one + 1];
                }
                
                // pick 1
                if(one > 0) {
                    val += ((1.0 * one) / n) * dp[three][two][one - 1];
                }

                double p0 = (1.0 * zero) / n; // probability to pick a dish with 0 sushi 
                
                val = val / (1 - p0);

                dp[three][two][one] = val;
            }

        }

    }
    


    cout << setprecision(9);
    cout << fixed;
    // the final answer is stored in the dp cell which has the given number of sushi dishes
    cout << dp[cnt[3]][cnt[2]][cnt[1]] << "\n";
    return 0;
}







