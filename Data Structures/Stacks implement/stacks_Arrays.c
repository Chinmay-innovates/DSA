#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 10

struct Stack {
    int items[MAX_SIZE];
    int top;
};
// Function to initialize the stack
void initialize(struct Stack *s) {
    s->top = -1;
}
// Function to check if the stack is empty
int isEmpty(struct Stack *s) {
    return s->top == -1;
}
// Function to check if the stack is full
int isFull(struct Stack *s) {
    return s->top == MAX_SIZE - 1;
}
// Function to push an element onto the stack
void push(struct Stack *s, int value) {
    if (isFull(s)) {
        printf("Stack Overflow! Cannot push element %d\n", value);
    } else {
        s->top++;
        s->items[s->top] = value;
        printf("Pushed: %d \n", value);
    }
}
// Function to pop an element from the stack
int pop(struct Stack *s) {
    if (isEmpty(s)) {
        printf("Stack Underflow! Cannot pop from an empty stack\n");
        return -1; // Return a value indicating failure
    } else {
        int popped = s->items[s->top];
        s->top--;
        printf("Popped: %d\n", popped);
        return popped;
    }
}
// Function to display the stack contents
void display(struct Stack *s) {
    if (isEmpty(s)) {
        printf("Stack is empty\n");
    } else {
        printf("Stack contents:\n");
        for (int i = s->top; i >= 0; i--) {
            printf("%d\n", s->items[i]);
        }
    }
}
int main() {
    struct Stack stack;
    initialize(&stack);

    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 50);
    push(&stack, 30);
    push(&stack, 90);
    push(&stack, 60);
    
    display(&stack);

    pop(&stack);
    display(&stack);

    push(&stack, 30);
    display(&stack);

    push(&stack, 40);
    display(&stack);

    pop(&stack);
    display(&stack);

    push(&stack,60);
    display(&stack);

    pop(&stack);
    display(&stack);

    pop(&stack);
    display(&stack);

    pop(&stack);
    display(&stack);

    display(&stack);
    return 0;
}
