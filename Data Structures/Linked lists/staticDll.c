#include <stdio.h>
#include <stdlib.h>

// Node structure for the doubly linked list
struct Node {
    int data;
    struct Node* prev;
    struct Node* next;
};
// Function to create a new node
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    if (newNode == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }

    newNode->data = data;
    newNode->prev = NULL;
    newNode->next = NULL;

    return newNode;
}
// Function to insert a new node at the beginning of the list
void insertAtBeginning(struct Node** head, int data) {
    struct Node* newNode = createNode(data);

    if (*head != NULL) {
        newNode->next = *head;
        (*head)->prev = newNode;
    }

    *head = newNode;
}
// Function to delete a node with a specific key
void deleteNode(struct Node** head, int key) {
    struct Node* current = *head;

    // Traverse the list to find the node with the given key
    while (current != NULL) {
        if (current->data == key) {
            // Update pointers of the previous and next nodes
            if (current->prev != NULL)
                current->prev->next = current->next;
            else
                *head = current->next;

            if (current->next != NULL)
                current->next->prev = current->prev;

            // Free memory occupied by the deleted node
            free(current);
            return;
        }
        current = current->next;
    }

    // Node with the given key not found
    printf("Node with key %d not found\n", key);
}

// Function to print the doubly linked list
void printList(struct Node* node) {
    while (node != NULL) {
        printf("%d <-> ", node->data);
        node = node->next;
    }
    printf("NULL\n");
}
// Function to free the memory occupied by the doubly linked list
void freeList(struct Node* head) {
    while (head != NULL) {
        struct Node* temp = head;
        head = head->next;
        free(temp);
    }
}
int main() {
    struct Node* head = NULL;

    // Insert nodes at the beginning of the doubly linked list
    insertAtBeginning(&head, 4);
    insertAtBeginning(&head, 6);
    insertAtBeginning(&head, 3);  
    insertAtBeginning(&head, 7);
    insertAtBeginning(&head, 12);
    insertAtBeginning(&head, 2);


    printf("Original doubly linked list:\n");
    printList(head);

    // Delete a node with a specific key
    int keyToDelete =7;
    deleteNode(&head, keyToDelete);

    printf("Doubly linked list after deleting node with key %d:\n", keyToDelete);
    printList(head);

    // Free memory occupied by the remaining nodes
    freeList(head);

    return 0;
}
