package arvoreChar;
public class ArvoreChar{
    No raiz;
    public ArvoreChar(){
        raiz = null;
    }

    public void insert(char dado){
        No no = new No();
        no.dado = dado;
        no.esquerda = null;
        no.direita = null;
        insertLeaf(raiz, no);
    }
    
    private void insertLeaf(No raizSubArvore, No no){
        if(raiz == null){
            raiz = no;
        } else {
            if(no.dado < raizSubArvore.dado){
                if(raizSubArvore.esquerda == null){
                    raizSubArvore.esquerda = no;
                } else {
                    insertLeaf(raizSubArvore.esquerda, no);
                }
            }
            if(no.dado > raizSubArvore.dado){
                if(raizSubArvore.direita == null){
                    raizSubArvore.direita = no;
                } else{
                    insertLeaf(raizSubArvore.direita, no);
                }
            }
        }
    }
    
    public void search(char valor) throws Exception{
        try{
            No no = nodeSearch(raiz, valor);
            int level = nodeLevel(raiz, valor);
            System.out.println("Dado"+no.dado+" nível"+level);
        } catch(Exception e){
            throw new Exception("Valor não existente");
        }
    }

    private No nodeSearch(No raizSubArvore, char valor)  throws Exception {
        if (raiz == null){
            throw new Exception("Arvore vazia!");
        } else if(raizSubArvore.dado > valor){
            return nodeSearch(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado < valor){
            return nodeSearch(raizSubArvore.direita, valor);
        } else {
            return raizSubArvore;
        }
        
    }

    private int nodeLevel(No raizSubArvore, char valor) throws Exception {
        if (raiz == null){
            throw new Exception("Arvore vazia!");
        } else if (raizSubArvore.dado > valor){
            return 1 + nodeLevel(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado < valor){
            return 1 + nodeLevel(raizSubArvore.direita, valor);
        } else {
            return 0;
        }
    }
    
    public void infixSearch() throws Exception{
        infix(raiz);
    }

    private void infix(No raizSubArvore)throws Exception{
        if (raiz == null){
            throw new Exception("Arvore vazia!");
        } else {
            if(raizSubArvore.esquerda != null){
                infix(raizSubArvore.esquerda);
            }
            System.out.println(raizSubArvore.dado);
            System.out.println(" ");
            if ( raizSubArvore.direita != null){
                infix(raizSubArvore.direita);
            }
        }
    }

    public void prefixSearch() throws Exception{
        prefix(raiz);
    }

    private void prefix(No raizSubArvore)throws Exception{
    
        if(raiz == null){
           throw new Exception("Árvore vazia!");
        } else {
            System.out.println(raizSubArvore.dado);
            System.out.println(" ");
            if (raizSubArvore.esquerda != null){
                prefix(raizSubArvore.esquerda);
            }
            if(raizSubArvore.direita != null){
                prefix(raizSubArvore.direita);
            }
        } 
    }
    
    public void postfixSearch()throws Exception{
        postfix(raiz);
    }

    private void postfix(No raizSubArvore)throws Exception{
        if(raiz == null){
            throw new Exception("Árvore vazia!");
        } else {
            if(raizSubArvore.esquerda != null){
                postfix(raizSubArvore.esquerda);
            }
            if(raizSubArvore.direita != null){
                postfix(raizSubArvore.direita);
            }
            System.out.println(raizSubArvore.dado);
            System.out.println(" ");
        }
    }
    
    void remove(char valor)throws Exception{
        try{
            removeChild(raiz, valor);
        } catch(Exception e){
           throw new Exception("Valor não existente.");
        }
    }

    private No removeChild(No raizSubArvore, char valor)throws Exception{
        if(raiz == null){
            throw new Exception("Árvore vazia!");
        } else if(raizSubArvore.dado > valor){
            raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
        } else if(raizSubArvore.dado < valor){
            raizSubArvore.direita = removeChild(raizSubArvore.direita, valor);
        } else {   //Achou o nó
            if(raizSubArvore.esquerda == null && raizSubArvore.direita == null ){  //folha
                raizSubArvore = null;
            } else if (raizSubArvore.esquerda == null){    //nó com filho à direita
                raizSubArvore = raizSubArvore.direita;
            } else if (raizSubArvore.direita == null){     //nó com filho à direita
                raizSubArvore = raizSubArvore.esquerda;
            } else{
                No no = raizSubArvore.esquerda;
                while (no.direita != null){
                    no = no.direita;
                }
                raizSubArvore.dado = no.dado;
                no.dado = valor;
                raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
            }
        }
        return raizSubArvore;
    }
}