#include<stdio.h>
#include<stdlib.h>

struct Node{
    int data;
    struct Node* prev;
    struct Node* next;
};
/*creating newnode*/
struct Node* createNode(int data){
    struct Node* newnode = (struct Node*)malloc(sizeof(struct Node));
  if (newnode == NULL)
    {
        printf("Mem alloc. failed\n");
        exit(1);
    }
    newnode->data = data;
    newnode->next = NULL;
    newnode->prev = NULL;
    return newnode;
}
void insert_at_beg(struct Node** head,int data){
    struct Node* newnode = createNode(data);
    if (*head != NULL)
    {
      newnode->next = *head;
      (*head)->prev = newnode;
    }
    *head = newnode;
}

void del_node(struct Node** head,int key){
    struct Node* current = *head;
    while(current!=NULL){
        if(current->data==key){
            if(current->prev != NULL)
              current->prev->next=current->next; 
            else
             *head = current->next;

          if(current->next != NULL)
             current->next->prev=current->prev;
             printf("%d is deleted",current->data);
            
            free(current); 
        return;   
    }
        current = current->next;
    }
    printf("node with %d key not found",current->data);
    

}
void print_list(struct Node* node){
    while (node!= NULL)
    {
        printf("%d <=> ",node->data);
        node=node->next;
    }
    printf("NULL\n");
}
void free_list(struct Node*head){
    while (head!= NULL)
    {
       struct Node *temp = head;
       head = head->next;
       free(temp);
    }
}
int main()
{
    struct Node*head =NULL;
int choice,data;
int isValidchoice=0;
do{
  printf("\n1.insert at begining\n");
  printf("2.delete a node\n");
  printf("3.print list\n");
  printf("4.Exit");
  while (!isValidchoice)

  {
    printf("\nenter choice :");
    if(scanf("%d",&choice)==1){
        if(choice==4)
         break;
        switch(choice) 
        {  
          case 1:
           printf("Enter data to insert: ");
           scanf("%d", &data);
           insert_at_beg(&head, data);
           break;
          case 2:
           printf("Enter data to delete: ");
           scanf("%d", &data);
           del_node(&head, data);
           break;
          case 3:
           printf("\nDoubly linked list:\n");
           print_list(head);
           break;
          case 4: break;
         
           default: printf("\ninvalid input!\n\n");
           break;
                }
            }
        else{
            while(getchar()!='\n');
             printf("Enter integer\n");
        }
    }
  }while(choice!=4);
  free_list(head);
  return 0;




}
