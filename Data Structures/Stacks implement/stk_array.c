#include <stdio.h>
#include <stdlib.h>
#define MAX 5
int push(int stack[], int element);
int pop(int stack[], int *element);
void displaymenu();
void print(int stack[]);
int top = -1;
int stack[MAX];
void main()
{
    int data, status, choice;
   
    printf("****Stack Implementation using Array****\n");
    printf("========================================\n");
    displaymenu();
    fflush(stdin);
    while (1)
    {
        printf("\nenter ur choice: ");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
            printf("\nEnter the element: ");
            fflush(stdin);
            scanf("%d", &data);
            status = push(stack, data);
            if (status == -1)
                printf("\n stack overflow on PUSH");
            else
                print(stack);
                break;
        case 2:
            status = pop(stack, &data);
            if (status == -1)
                printf("\n stack overflow on POP");
            else
            {
                printf("popped: %d", data);
                print(stack);
            }
        case 3:
            print(stack);
            break;
        default:
            printf("End of ur program..");
            exit(0);
        }
    }
}
void displaymenu()
{
    printf("\n1.PUSH");
    printf("\n2.POP");
    printf("\n3.VIEW");
    printf("\n4.EXIT");
}
int push(int stk[], int element)
{
    if (top < (MAX - 1))
    {
        stk[++top] = element;
        return 0;
    }
    else
        return -1;
}
int pop(int stk[], int *element)
{
    if (top > 0)
    {
        *element = stk[top--];
        return 0;
    }
    else
        return -1;
}
void print(int stk[])
{

    int i;
    if (top == -1)
        printf("Stack is empty!!");

    else
    {
        printf("\nThe contents of stack is...Top");
        for (i = top; i > 0; i--)
         printf("--> %d", stk[i]);
        if (top == (MAX - 1))
        printf("\nstack is full");
    }
}