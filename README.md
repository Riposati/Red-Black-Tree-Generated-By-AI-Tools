PROJETO CRIADO POR MIM E IA GENERATIVAS COMO CHAT GPT, AMAZON Q

# 🌳 Red-Black Tree em Java

Este projeto implementa uma **Árvore Rubro-Negra (Red-Black Tree)** em Java, com operações de inserção, remoção, travessias e visualização da estrutura da árvore no console.

## 📚 O que é uma Árvore Rubro-Negra?

A Árvore Rubro-Negra é uma árvore binária de busca autobalanceada que garante complexidade de tempo **O(log n)** para inserção, remoção e busca. Ela mantém suas propriedades através de **cores (vermelho/preto)** e **rotações**.

## ✅ Funcionalidades

- ✅ Inserção de valores
- ✅ Remoção de valores
- ✅ Impressão da árvore em:
  - Ordem (InOrder)
  - Pré-Ordem (PreOrder)
  - Pós-Ordem (PostOrder)
- ✅ Impressão visual da estrutura da árvore com cores
- ✅ Balanceamento automático
- ✅ Implementação das principais operações auxiliares:
  - `rotateLeft`
  - `rotateRight`
  - `flipColors`
  - `moveRedLeft`
  - `moveRedRight`
  - `fixUp`

## 🧪 Exemplo de uso

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

## 🖼️ Saída esperada (exemplo)

```
In Order: 10 20 30 40 
Pre Order: 20 10 30 40 
Post Order: 10 40 30 20 
└── 30(B)
    ├── 40(R)
    └── 10(R)

Após remoções:
└── 30(B)
    └── 10(R)
```

## 🧠 Como funciona o balanceamento?

- Nós recém-inseridos são vermelhos.
- As rotações e a troca de cores (`flipColors`) mantêm as propriedades da árvore rubro-negra.
- Durante a remoção, as operações `moveRedLeft` e `moveRedRight` são utilizadas para garantir que o nó alvo esteja preparado para remoção sem quebrar as propriedades da árvore.

## 🛠️ Estrutura do projeto

```
src/
├── org.example/
│   ├── RedBlackTree.java
│   └── Main.java
```

## 🚀 Como executar

1. Compile os arquivos:
   ```bash
   javac org/example/*.java
   ```

2. Execute a aplicação:
   ```bash
   java org.example.Main
   ```

## 📌 Observações

- Esta implementação é educacional e tem foco em clareza, não em performance máxima.
- O método `printTree` é útil para depuração, mostrando a estrutura da árvore e suas cores (R para vermelho, B para preto).
