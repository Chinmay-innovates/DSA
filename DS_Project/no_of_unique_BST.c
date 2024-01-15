#include <stdio.h>

// The number of unique BSTs for a given number of nodes can be calculated using the Catalan number. The nth Catalan number, Cn, represents the number of unique BSTs with n nodes.

// The formula for calculating the nth Catalan number is given by:
//            2n
// Cn = 1/n+1   C
//               n

// Function to calculate the binomial coefficient
long long binomialCoefficient(int n, int k) {
    if (k > n - k) {
        k = n - k;
    }

    long long result = 1;

    for (int i = 0; i < k; ++i) {
        result *= (n - i);
        result /= (i + 1);
    }

    return result;
}

// Function to calculate the nth Catalan number
long long catalanNumber(int n) {
    return binomialCoefficient(2 * n, n) / (n + 1);
}

int main() {
    int n;

    printf("Enter the number of nodes in the BST: ");
    scanf("%d", &n);

    // Calculate the number of unique BSTs
    long long numBSTs = catalanNumber(n);

    printf("Number of unique BSTs with %d nodes: %lld\n", n, numBSTs);

    return 0;
}

