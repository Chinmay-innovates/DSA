// C program to Implement Tree-Traversal-Methords in BST
/* NON RECURSIVE*/
#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node
struct TreeNode
{
    int data;
    struct TreeNode *left;
    struct TreeNode *right;
};

// Definition for a stack node
struct StackNode
{
    struct TreeNode *node;
    struct StackNode *next;
};

// Function to create a new tree node
struct TreeNode *createNode(int data)
{
    struct TreeNode *newNode = (struct TreeNode *)malloc(sizeof(struct TreeNode));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
    return newNode;
}

// Function to create a new stack node
struct StackNode *createStackNode(struct TreeNode *node)
{
    struct StackNode *stackNode = (struct StackNode *)malloc(sizeof(struct StackNode));
    stackNode->node = node;
    stackNode->next = NULL;
    return stackNode;
}

// Function to push a node onto the stack
void push(struct StackNode **stack, struct TreeNode *node)
{
    struct StackNode *stackNode = createStackNode(node);
    stackNode->next = *stack;
    *stack = stackNode;
}

// Function to pop a node from the stack
struct TreeNode *pop(struct StackNode **stack)
{
    if (*stack == NULL)
        return NULL;

    struct TreeNode *poppedNode = (*stack)->node;
    struct StackNode *temp = *stack;
    *stack = (*stack)->next;
    free(temp);
    return poppedNode;
}

// Function for preorder traversal using a stack
void preorderTraversal(struct TreeNode *root)
{
    if (root == NULL)
        return;

    struct StackNode *stack = NULL;
    push(&stack, root);

    while (stack != NULL)
    {
        struct TreeNode *current = pop(&stack);
        printf("%d ", current->data);

        // Push right child first so that left child is processed first
        if (current->right != NULL)
            push(&stack, current->right);

        if (current->left != NULL)
            push(&stack, current->left);
    }
}

// Function for inorder traversal using a stack
void inorderTraversal(struct TreeNode *root)
{
    if (root == NULL)
        return;

    struct StackNode *stack = NULL;
    struct TreeNode *current = root;

    while (current != NULL || stack != NULL)
    {
        while (current != NULL)
        {
            push(&stack, current);
            current = current->left;
        }

        current = pop(&stack);
        printf("%d ", current->data);

        current = current->right;
    }
}

// Function for postorder traversal using two stacks
void postorderTraversal(struct TreeNode *root)
{
    if (root == NULL)
        return;

    struct StackNode *stack1 = NULL;
    struct StackNode *stack2 = NULL;
    push(&stack1, root);

    while (stack1 != NULL)
    {
        struct TreeNode *current = pop(&stack1);
        push(&stack2, current);

        if (current->left != NULL)
            push(&stack1, current->left);

        if (current->right != NULL)
            push(&stack1, current->right);
    }

    while (stack2 != NULL)
    {
        struct TreeNode *current = pop(&stack2);
        printf("%d ", current->data);
    }
}

int main()
{
    // Creating a sample binary tree
    /*   1
        / \
       2   3
          / \
         4   5
    */
    struct TreeNode *root = createNode(1);
    root->left = createNode(2);
    root->right = createNode(3);
    root->left->left = createNode(4);
    root->left->right = createNode(5);

    printf("Inorder Traversal: ");
    inorderTraversal(root);
    printf("\n");

    printf("Preorder Traversal: ");
    preorderTraversal(root);
    printf("\n");

    printf("Postorder Traversal: ");
    postorderTraversal(root);
    printf("\n");

    return 0;
}
/*                                        OUTPUT
PS C:\VS-code\.vscode\Data Structures> cd "c:\VS-code\DataStructures\DS_Project\" ;
if ($?) { gcc TreeTraversal.c -o TreeTraversal } ; if ($?) { .\TreeTraversal }

Inorder Traversal: 4 2 5 1 3
Preorder Traversal: 1 2 4 5 3
Postorder Traversal: 4 5 2 3 1

PS C:\VS-code\Data Structures\DS_Project>
*/