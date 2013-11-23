package j3chess;

public class Edge{
	public Field leftField;
	public Field rightField;
	
	public Edge(Field leftField,Field rightField){
		this.leftField=leftField;
		this.rightField=rightField;
	}
	
	public Boolean isEqual(Edge edge){
		if(this.leftField==edge.leftField&&this.rightField==edge.rightField){
			return true;
		}
		return false;
	}
}