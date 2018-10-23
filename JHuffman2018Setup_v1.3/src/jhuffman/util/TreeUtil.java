package jhuffman.util;

import java.util.Stack;
import jhuffman.ds.Node;

public class TreeUtil
{
	private Stack<Node> pila=null;
	private Stack<String> pilaCod=null;

	public TreeUtil(Node root)
	{
		pila=new Stack<Node>();
		pilaCod=new Stack<String>();
		
		pila.push(root);
		pilaCod.push("");
	}
	
	public static Node makeTree(SortedList<Node> list){
		Node root = null;
		while (list.size() > 1){
			Node d = list.getLast();
			list.remove(d);
			Node i = list.getLast();
			list.remove(i);
			
			int n = (int) (i.getN()+d.getN());
			Node padre = new Node (n+256, n, i, d);
			list.add(padre, new ComparatorCharFreq());
		}
		root = list.get(0);
		return root;
	}
	
	public Node next(StringBuffer cod)
	{
		boolean hoja=false;
		Node p=null;
		Node aux=null;
		String zz=null;
		
		while( pila.size()>0 && !hoja )
		{
			p=pila.pop();
			zz=pilaCod.pop();
		
			if( p.getDer()!=null )
			{
				pila.push(p.getDer());
				aux=new Node();
				aux.setN(1);
				pilaCod.push(zz+"1");
			}

			if( p.getIzq()!=null )
			{
				pila.push(p.getIzq());
				aux=new Node();
				aux.setN(0);
				pilaCod.push(zz+"0");
			}
						
			if( p.getIzq()==null && p.getDer()==null )
			{
				hoja=true;
				cod.delete(0, cod.length());
				cod.append(zz);
			}
			else
			{
				p=null;
			}
		}
		
		return p;
	}
}
