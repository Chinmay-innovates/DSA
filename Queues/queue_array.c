#include <stdio.h>
#include <stdlib.h>
#define QSIZE 2
#define FULL 0
#define EMPTY 1
#define SOMEDATA 2

void enqueue(int queue[], int data);
int dequeue(int queue[]);
void displaymenu();
void view(int queue[]);
int front, rear, qstatus;
void main()
{
    printf("----ARRAY IMPLEMANTATION OF QUEUE---\n");
    int queue[QSIZE], choice, data;
    // clrscr();
    displaymenu();
    rear = -1;
    front = -1;
    qstatus = EMPTY;
    while (1)
    {
        printf("\n\nchoice? ");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
            if (qstatus == FULL)
                printf("\nEnter the element: ");
            else
            {
                // fflush(stdin);
                printf("\nEnter the element: ");
                scanf("%d", &data);
                enqueue(queue, data);
                view(queue);
            }
            break;
        case 2:
            if (qstatus == EMPTY)
                printf("\n Queue Underflow on DEQUEUE ");
            else
            {
                printf("\n The dequeued value is %d", dequeue(queue));
                view(queue);
            }
            break;
        case 3:
            view(queue);
            break;
        case 4: printf("\nExiting program...");exit(0); break;    
        default:
            printf("\n Enter valid number");
        }

    }
}

void displaymenu()
{
    printf("\n\t 1. Enqueue");
    printf("\n\t 2. Dequeue");
    printf("\n\t 3. View");
    printf("\n\t 4. Exit");
}
void enqueue(int queue[], int data)
{
    qstatus = SOMEDATA;
    rear++;
    if (rear == QSIZE)
        rear = 0;
    if ((front == -1 && rear == (QSIZE - 1)) || rear == front)
        qstatus = FULL;
    queue[rear] = data;
}
int dequeue(int queue[])
{
    front++;
    if (front == QSIZE)
        front = 0;
    if (front == rear)
        qstatus = EMPTY;
    else
        qstatus = SOMEDATA;
    return (queue[front]);
}

void view(int queue[])
{
    int i;
    if (qstatus == EMPTY)
        printf("\n Queue is EMPTY !!!");
    else
    {
        i = front;
        printf("\n Queue contains ....\n Front --> ");
        do
        {
            printf(" %d --> ", queue[i = (i + 1) % QSIZE]);
        } while (i != rear);
        printf("Rear \n");
        if (qstatus == FULL)
            printf("\n Queue is FULL !!!  QUEUE SIZE IS %d",QSIZE);
    }
}