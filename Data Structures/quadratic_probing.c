
#include <stdio.h>
#define SIZE 13

int hashTable[SIZE] = {0};


int hashFunction(int key) {
    return key % SIZE;
}

void insert(int key) {
    int index = hashFunction(key);

    if (hashTable[index] == 0) {
        hashTable[index] = key;
        printf("Element %d inserted at index %d\n", key, index);
    } else {
        int i = 1;
        int newIndex;

        do {
            newIndex = (index + i * i) % SIZE;

            if (hashTable[newIndex] == 0) {
                hashTable[newIndex] = key;
                printf("Element %d inserted at index %d\n", key, newIndex);
                return;
            }

            i++;
        } while (newIndex != index);

        printf("Hash table is full. Cannot insert %d\n", key);
    }
}

void displayHashTable() {
    printf("Hash Table:\n");
    for (int i = 0; i < SIZE; i++) {
        printf("Index %d: ", i);
        if (hashTable[i] != 0) {
            printf("%d", hashTable[i]);
        } else {
            printf("Empty");
        }
        printf("\n");
    }
}

int main() {
    int keys[] = {65,34,79,114,26,85,55,89,22,98};
    int numKeys = sizeof(keys) / sizeof(keys[0]);

    for (int i = 0; i < numKeys; i++) {
        insert(keys[i]);
    }

    displayHashTable();

    return 0;
}
