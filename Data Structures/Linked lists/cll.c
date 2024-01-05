#include<stdio.h>
#include<stdlib.h>
typedef struct Node
{
	int info;
	struct Node*next;
} node;
node *front=NULL,*rear=NULL,*temp;
void create();

void del();
void display ();
int main (){
	int ch;
	do{
		printf("\n menu");
		printf("\n1.create the element");
		printf("\n2.delete the element");
		printf("\n3.display the element");
		printf("\n4.exit");
		printf("\nenter your choice : ");
		scanf("%d",&ch);
		 switch (ch){
			case 1: create();break;
			case 2:del();break;
			case 3:display();break;
			case 4:return 1;
			default:printf("\ninvalid choice");
		 }
	}while(1);
	return 0;
}
void create(){
	node *newnode;
	newnode=(node*)malloc(sizeof(node));
	printf("enter Node value: ");
	scanf("%d",&newnode->info);
	newnode->next=NULL;
	if(rear==NULL)
	{
		front=rear=newnode;
	}
	else{
		rear->next=newnode;
		rear=newnode;
	}
	rear->next=front;	
}
void del(){
	temp=front;
	if(front==NULL)
	 printf("\n :underflow: " );
	 else{


		if(front==rear)
		{
			printf("\n%d is deleted!",front->info);
			front=rear=NULL;
		}

		else{
        printf("\n%d is deleted! ", front->info);
		front=front->next;
		rear->next=front;
	   }
    temp->next=NULL;
	 free(temp);
	} 
}
void display(){
	temp=front;
	if(front==NULL)
	 printf("\nEmpty,can not delete elemnts!!");

	 else{
		printf("\n");
		for (;temp!=rear;temp=temp->next)
		{
			printf("\n %d address=%u next=%u \t",temp->info,temp,temp->next);
		}
			printf("\n %d address=%u next=%u \t",temp->info,temp,temp->next);

	}
}




 







