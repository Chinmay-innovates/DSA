#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#define TRUE 1
#define FALSE 0
#define FULL 10
typedef struct node
{
    int data;
    struct node *next;
} node;

typedef struct queue
{
    int count;
    node *front;
    node *rear;
} queue;

void initialize(queue *q)
{
    q->count = 0;
    q->front = NULL;
    q->rear = NULL;
}
int isEmpty(queue *q)
{
    return (q->rear == NULL);
}
void Enqueue(queue *q, int value)
{
    if (q->count < FULL) // count < 10
    {
        node *temp;
        temp = malloc(sizeof(node));
        temp->data = value;
        temp->next = NULL;
        if (!isEmpty(q))
        {
            q->rear->next = temp;
            q->rear = temp;
        }
        else
        {
            q->front = q->rear = temp;
        }
      q->count++;
    }
    else
        printf("List is Full !\n");
}

int Dequeue(queue *q)
{
    node *ptr;
    int n = q->front->data;
    ptr = q->front;
    q->front = q->front->next;
    q->count--;
    free(ptr);
    return(n);
}

void display(node *head)
{
    if (head == NULL)
    {
        printf("NULL\n");
    }
    else{
        printf("%d->%d\n",head->data,&head->data);
        display(head->next);
    }
}

// driver code
int maain(){
    queue *q;
    q=malloc(sizeof(queue));
    Enqueue(q,10);
    Enqueue(q,50);
    Enqueue(q,60);
    Enqueue(q,70);
    Enqueue(q,30);
    Enqueue(q,60);
    
    printf("Queue before deletion\n");
    display(q->front);

    Dequeue(q);
    Dequeue(q);

    printf("Queue after deletion\n");
    display(q->front);

return 0;
}