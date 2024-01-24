// pattern matching alg in C
// Boyer Moorie
#include <stdio.h>
#include <string.h>

#define NO_OF_CHARS 256

int max(int a, int b)
{
    return (a > b) ? a : b;
}

void badCharHeuristic(char *pattern, int patternLength, int badchar[NO_OF_CHARS])
{
    for (int i = 0; i < NO_OF_CHARS; i++)
    {
        badchar[i] = -1;
    }

    for (int i = 0; i < patternLength; i++)
    {
        badchar[(int)pattern[i]] = i;
    }
}

void searchBoyerMoore(char *text, char *pattern)
{
    int textLength = strlen(text);
    int patternLength = strlen(pattern);

    int badchar[NO_OF_CHARS];
    badCharHeuristic(pattern, patternLength, badchar);

    int s = 0; // s is the shift of the pattern with respect to text
    while (s <= (textLength - patternLength))
    {
        int j = patternLength - 1;

        // Keep reducing the index of pattern while characters of pattern and text are matching
        while (j >= 0 && pattern[j] == text[s + j])
            j--;

        // If the pattern is present at the current shift, print the index
        if (j < 0)
        {
            printf("Pattern found at index %d\n", s);

            // Shift the pattern so that the next character in text aligns with the last occurrence of it in pattern
            s += (s + patternLength < textLength) ? patternLength - badchar[text[s + patternLength]] : 1;
        }
        else
        {
            // Shift the pattern so that the bad character in text aligns with the last occurrence of it in pattern
            s += max(1, j - badchar[text[s + j]]);
        }
    }
}

int main()
{
    char text[] = "THIS IS A TEST TEXT";
    char pattern[] = "TEST";

    searchBoyerMoore(text, pattern);

    return 0;
}
/*                                        OUTPUT

PS C:\VS-code\.vscode\Data Structures\DS_Project> cd "c:\VS-code\.vscode\Data Structures\DS_Project\" ; if ($?) { gcc BM.c -o BM } ; if ($?) { .\BM }

Pattern found at index 10

PS C:\VS-code\.vscode\Data Structures\DS_Project> 

*/