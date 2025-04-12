# 🌳 Red-Black Tree in Java  
**Project created by me and generative AIs like ChatGPT and Amazon Q**

This project implements a **Red-Black Tree** in Java, including insertion, deletion, tree traversals, and visual printing of the tree structure in the console.

## 📚 What is a Red-Black Tree?

A Red-Black Tree is a **self-balancing binary search tree** that guarantees **O(log n)** time complexity for insertion, deletion, and search operations. It maintains its properties using **node colors (red/black)** and **rotations** to balance itself.

## ✅ Features

- ✅ Value insertion  
- ✅ Value deletion  
- ✅ Tree traversal printing:
  - In-Order
  - Pre-Order
  - Post-Order  
- ✅ Visual representation of the tree structure with node colors  
- ✅ Automatic balancing  
- ✅ Core helper operations:
  - `rotateLeft`
  - `rotateRight`
  - `flipColors`
  - `moveRedLeft`
  - `moveRedRight`
  - `fixUp`

## 🧪 Usage Example

```java
public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);

        System.out.print("In Order: ");
        tree.printInOrder(tree.getRoot());

        System.out.print("\nPre Order: ");
        tree.printPreOrder(tree.getRoot());

        System.out.print("\nPost Order: ");
        tree.printPostOrder(tree.getRoot());

        tree.printTree();

        tree.delete(20);
        tree.delete(40);

        tree.printTree();
    }
}
```

## 🖼️ Expected Output (Example)

```
In Order: 10 20 30 40 
Pre Order: 20 10 30 40 
Post Order: 10 40 30 20 
└── 30(B)
    ├── 40(R)
    └── 10(R)

After deletions:
└── 30(B)
    └── 10(R)
```

## 🧠 How Balancing Works

- Newly inserted nodes are red.
- Rotations and color flipping (`flipColors`) maintain the red-black tree properties.
- During deletion, `moveRedLeft` and `moveRedRight` are used to prepare the target node for safe removal without violating tree properties.

## 🛠️ Project Structure

```
src/
├── org.example/
│   ├── RedBlackTree.java
│   └── Main.java
```

## 🚀 How to Run

1. Compile the files:

   ```bash
   javac org/example/*.java
   ```

2. Run the application:

   ```bash
   java org.example.Main
   ```

## 📌 Notes

- This implementation is educational and prioritizes **clarity over performance**.
- The `printTree` method is useful for **debugging**, displaying the tree structure with node colors:
  - `(R)` for red
  - `(B)` for black
