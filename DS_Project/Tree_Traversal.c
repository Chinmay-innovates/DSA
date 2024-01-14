//  C program to Implement Tree-Traversal-Methords in BST: In-Order , Pre-order, Post-Order
#include <stdio.h>
#include <stdlib.h>
// Struture for a BST node
struct node
{
    int data;
    struct node *left, *right;
};
// Function to create a newNode in BST
struct node *newNode(int data)
{
    struct node *newNode = (struct node *)malloc(sizeof(struct node));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
};
// Function to insert data in BST
struct node *insert(struct node *root, int data)
{
    if (root == NULL)
        return newNode(data);

    if (data < root->data)
        root->left = insert(root->left, data);

    else if (data > root->data)
        root->right = insert(root->right, data);
    return root;
}
// function to print In-Order Traversal
void Inorder(struct node *root)
{
    if (root != NULL)
    {
        Inorder(root->left);
        printf("%d ", root->data);
        Inorder(root->right);
    }
}
// function to print Pre-Order Traversal
void Preorder(struct node *root)
{
    if (root != NULL)
    {
        printf("%d ", root->data);
        Preorder(root->left);
        Preorder(root->right);
    }
}
// function to print Post-Order Traversal
void Postorder(struct node *root)
{
    if (root != NULL)
    {
        Postorder(root->left);
        Postorder(root->right);
        printf("%d ", root->data);
    }
}
// Driver Code
int main(void)
{
    int i;
    struct node *root = NULL;
    int data[] = {9, 7, 5, 8, 14, 11, 16};
    int numKeys = sizeof(data) / sizeof(data[0]);
    // Building the BST
    for (int i = 0; i < numKeys; i++)
        root = insert(root, data[i]);
    printf("\n\nIn-order Traversal of BST: ");
    Inorder(root);

    printf("\n\nPre-order Traversal of BST: ");
    Preorder(root);

    printf("\n\nPost-order Traversal of BST: ");
    Postorder(root);
}