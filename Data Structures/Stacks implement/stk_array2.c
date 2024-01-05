#include<stdio.h>
#include<conio.h>
int stack[100],i,j,choice=0,n,top=-1;
void push();
void pop();
void show();
void main(){
    printf("enter no.of elements in the stack: \n");
    scanf("%d",&n);
    printf("******Stack operation using array******"); /*Simple and easy Stack _by array implementation*/
    printf("\n-----------------------------------\n");
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
       case 3 : show();break;
       case 4 : printf("Exiting..");break;

       default: printf("\nPls Enter a Valid Choice\n");
        break;
       }
    }
}

void push(){
    int value;
    if(top==n)
     printf("\nOverflow\n");
    else{
        printf("Enter the value?\n");
        scanf("%d",&value);
        top= top+1;
        stack[top] = value;
        printf("item Pushed");
    } 
}
void pop(){
    if(top==-1)
     printf("\nUnderflow\n");
    else{
        top= top-1;
        printf("item popped ");
    } 
}
void show(){
   printf("\nStack Contents: \n\n");
    for(i=top;i>=0;i--){
    printf("|\t%d\t|\n",stack[i]);
    printf("-----------------\n");
    }
    if(top==-1)
    printf("Stack is Empty...");
}






