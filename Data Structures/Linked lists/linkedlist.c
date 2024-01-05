#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

struct node
{
 int data;
 struct node *next;
}*first=NULL;
int insertb ;
void insert()
{
 struct node *newrec,*old;
 newrec=(struct node*)malloc(sizeof(struct node));
 printf("enter the data: ");
 scanf("%d",&newrec->data);
  newrec->next=NULL;
  if(first==NULL)
   first=newrec;
   else
   {
    old=first;
    while(old->next!=NULL)
    old=old->next;
    old->next=newrec;
   }
}
void insertbeg()
{
 struct node *newrec;
 newrec=(struct node*)malloc(sizeof(struct node));
  printf("Enter the data: ");
 scanf("%d",&newrec->data);
 newrec->next=first;
 first=newrec;
}
void insertafter()
{
 int pos,i;
 struct node *newrec,*old;
 newrec=(struct node*)malloc(sizeof(struct node));
  printf("Enter the data: ");
 scanf("%d",&newrec->data);
 printf("Enter position after which data to be added: ");
 scanf("%d",&pos);
 old=first;
 for(i=0;i<pos-1;i++)
 {
  old=old->next;
  if(old==NULL)
   {
    printf("There are less than %d elements ",pos);
    return;
   }
 }
 newrec->next=old->next;
 old->next=newrec;
}
void delnode()
{
 int x;
 struct node *old,*a;
 printf("enter data to be deleted: ");
 scanf("%d",&x);
 old=first;
 while(old->data!=x && old!=NULL)
  {
   a=old;
   old=old->next;
  }
 if(old==NULL)
 {
  printf("data doesnt exist\n");
  return;
 }
 if(old==first)
  first=old->next;
 else
  a->next=old->next;
 free(old);
}
void display()
{
 struct node *old;
 old=first;
 printf("Linked List contains:\n");
 printf("Head=> ");
  while(old!=NULL)
  {
   printf("||%u||%d|| ",&old->data,old->data);
   old=old->next;
  }
 printf(" <=tail\n");
}
void main ()
{

 int ch;
 //clrscr();
 printf("Menu\n");
 printf("1.Add a node\n");
 printf("2.Insert at begining \n");
 printf("3.Insert in between nodes \n");
 printf("4.delete a node \n");
 printf("5.display a node \n");
 printf("6.exit \n");
 do
 {
  printf("Enter your choice: ");
  scanf("%d",&ch);
  switch(ch)
  {
   case 1: insert();display();break;
   case 2: insertbeg();display();break;
   case 3: insertafter();display();break;
   case 4: delnode();display();break;
   case 5: display();break;
   case 6: break;
   default: printf("wrong choice\n");
  }
 }
 while(ch!=6);
}