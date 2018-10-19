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
