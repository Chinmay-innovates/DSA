#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0
#define QSIZE 10 // size of queue
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
void enqueue(queue *q, int value)
{
    if (q->count < QSIZE) // SIZE = 10
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
        printf("Enqueued:%d\n", value);
        q->count++;
    }
    else
    {
        printf("list is Full!\nCannot Enqueue:%d\n", value);
    }
}
int dequeue(queue *q)
{
    node *temp;
    int n = q->front->data;
    temp = q->front;
    q->front = q->front->next;
    q->count--;
    free(temp);
    printf("Dequeued:%d\n", n);
    return (n);
}
void display(node *head)
{
    if (head == NULL)
    {
        printf(" NULL\n");
    }
    else
    {

        printf(" %d --> %d \n", head->data, head->next);
        display(head->next);
    }
}
int main()
{
    queue *q;
    q = malloc(sizeof(queue));
    initialize(q);
    enqueue(q, 10);
    enqueue(q, 20);
    enqueue(q, 30);
    enqueue(q, 40);
    enqueue(q, 50);
    enqueue(q, 60);
    enqueue(q, 70);

    printf("Queue before dequeue: \n");
    display(q->front);

    dequeue(q);
    dequeue(q);
    dequeue(q);
    printf("Queue after dequeue: \n");
    display(q->front);
    return 0;
}