package j3chess;

public class Edge{
	public Field leftField;
	public Field rightField;
	
	public Edge(final Field leftField,final Field rightField){
		this.leftField=leftField;
		this.rightField=rightField;
	}
	
	public final Boolean isEqual(final Edge edge){
		if(this.leftField==edge.leftField&&this.rightField==edge.rightField){
			return true;
		}
		return false;
	}
}