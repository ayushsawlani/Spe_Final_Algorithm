package Spe_Final_Source;

public class Pair<T1, T2>{
	private T1 mFirst;
	private T2 mSecond;
	
	public Pair(T1 first, T2 second) {
		this.mFirst = first;
		this.mSecond = second;
	}
	

	public T1 getFirst() {
		return this.mFirst;
	}
	
 
	public T2 getSecond() {
		return this.mSecond;
	}

	@Override
	public boolean equals(Object first) {
		return ((((Pair)first).getFirst()).equals(this.mFirst) && (((Pair)first).getSecond()).equals(this.mSecond)); 
	}
	
	
 	@Override
	public String toString() {
		return "< " + mFirst.toString() + ", " + mSecond.toString() + " >";
	}
	@Override
	public int hashCode()
	{
		int p1 = 999999503;
        int p2 = 1000000007;
        return (this.getFirst().hashCode() + p1*this.getSecond().hashCode())%p2;
 
	}

}