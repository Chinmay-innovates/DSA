#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
void push();
void pop();
void display();
struct node{
    int val;
    struct node *next;
};

struct node *head;

void main(){
    int choice=0;
    printf("******Stack operation u4sing Linked List******"); /*Simple and easy Stack _by pointers implementation*/
    printf("\n----------------------------------------------\n");
    while (choice!=4)
    {
       printf("\nchoose one from below options..\n");
       printf("\n1.PUSH\n2.POP\n3.SHOW\n4.EXIT\n");
       printf("\nEnter your choice: ");
       scanf("%d",&choice);
       switch (choice)
       {
       case 1 : push();break;
       case 2 : pop();break;
       case 3 : display();break;
       case 4 : printf("Exiting..");break;

       default: printf("\nPls Enter a Valid Choice\n");
        break;
       }
    }
}
void push(){
    int val;
    struct node *ptr = (struct node*)malloc(sizeof(struct node));
    if(ptr==NULL)
     printf("\nnot able topush element\n");
    else{
        printf("Enter the value");
        scanf("%d",&val);
        if(head==NULL){
            ptr->val=val;
            ptr->next=NULL;
            head=ptr;
        }
        else{
            ptr->val=val;
            ptr->next=head;
            head=ptr;
        }
 printf("Item Pushed\n");
    } 
}
void pop(){
    int item;
    struct node *ptr;
    if(head==NULL)
     printf("\nUnderflow\n");
    else{
        item= head->val;
        ptr=head;
        head=head->next;
        free(ptr);
        printf("\nItem popped\n");
    }
}
void display(){
    int i;
    struct node *ptr;
    ptr=head;
    if(ptr==NULL)
     printf("Stack is empty\n");
     else{
        printf("Printing stack elements...\n");
        while(ptr!=NULL){
            printf("%d\n",ptr->val);
            ptr=ptr->next;
        }
     }
}