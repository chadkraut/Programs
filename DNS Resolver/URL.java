package dns_resolver;

/**
 * A URL Object is a representation of the URL that we have been giving. 
 * It knows how to compare URLs!
 * 
 * @author redwards
 *
 */
public class URL implements Comparable<URL> {
	String url;
	public int hashCode(){ return url.hashCode(); }
	public URL(String s){
		this.url = s;
	}
	public int compareTo(URL obj) {
		return (url).compareTo(obj.url);
	}
	public boolean equals(URL u){
		return url.equals(u.url);
	}
	public String toString(){ return url;}
}
