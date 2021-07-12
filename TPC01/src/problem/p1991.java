package problem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1991 {
    static class Node
    {
        char root;
        Node left;
        Node right;
        Node(int root)
        {
            this.root = (char)root;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Node[] my_bst = new Node[N];
        for(int i = 0; i<N;i++)
        {
            my_bst[i] = new Node('A'+i);
        }
        char[] data = new char[3];

        for(int i = 0 ; i < N;i++)
        {
            st = new StringTokenizer( br.readLine());
            data[0] = st.nextToken().charAt(0);
            data[1] = st.nextToken().charAt(0);
            data[2] = st.nextToken().charAt(0);
            if(data[1] != '.')
                my_bst[(data[0]-'A')].left = my_bst[(data[1]-'A')];
            else
                my_bst[(data[0]-'A')].left = null;
            if(data[2] != '.')
                my_bst[(data[0]-'A')].right = my_bst[(data[2]-'A')];
            else my_bst[(data[0]-'A')].right = null;
        }
        preTrev(my_bst[0]);
        System.out.println();
        inTrev(my_bst[0]);
        System.out.println();
        postTrev(my_bst[0]);
        System.out.println();

    }
    public static void preTrev(Node root)
    {
        if(root == null) return;
        System.out.print(root.root);
        preTrev(root.left);
        preTrev(root.right);
    }
    public static void inTrev(Node root)
    {
        if(root == null ) return;
        inTrev(root.left);
        System.out.print(root.root);
        inTrev(root.right);
    }
    public static void postTrev(Node root)
    {
        if(root == null) return;
        postTrev(root.left);
        postTrev(root.right);
        System.out.print(root.root);
    }

}
