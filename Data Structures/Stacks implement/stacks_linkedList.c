#include <stdio.h>
#include <stdlib.h>

// Structure to represent a node in the stack
struct Node {
    int data;
    struct Node *next;
};

// Structure to represent the stack
struct Stack {
    struct Node *top;
};

// Function to initialize the stack
void initialize(struct Stack *s) {
    s->top = NULL;
}

// Function to check if the stack is empty
int isEmpty(struct Stack *s) {
    return (s->top == NULL);
}

// Function to push an element onto the stack
void push(struct Stack *s, int value) {
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    if (newNode == NULL) {
        printf("Memory allocation failed. Cannot push element %d\n", value);
        return;
    }

    newNode->data = value;
    newNode->next = s->top;
    s->top = newNode;
    printf("Pushed:%d \n", value);
}

// Function to pop an element from the stack
int pop(struct Stack *s) {
    if (isEmpty(s)) {
        printf("Stack Underflow! Cannot pop from an empty stack\n");
        return -1; // Return a value indicating failure
    }

    int popped = s->top->data;
    struct Node *temp = s->top;
    s->top = s->top->next;
    free(temp);
    printf("Popped:%d\n", popped);
    return popped;
}

// Function to display the stack contents
void display(struct Stack *s) {
    if (isEmpty(s)) {
        printf("Stack is empty\n");
    } else {
        printf("Stack contents:\n");
        struct Node *current = s->top;
        while (current != NULL) {
            printf("%d\n", current->data);
            current = current->next;
        }
    }
}
int main() {
    struct Stack stack;
    initialize(&stack);

    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);
    push(&stack, 40);
    push(&stack, 50);
    push(&stack, 60);
    push(&stack, 70);
    push(&stack, 80);
    push(&stack, 90);
    push(&stack, 100);
    push(&stack, 101);
    
    display(&stack);

    pop(&stack);
    display(&stack);
    return 0;
}
