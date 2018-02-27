package dns_resolver;

import java.io.BufferedReader;
import java.io.FileReader;

import data_structures.Hash;
import data_structures.HashI;
import exceptions.FileFormatException;


/**
 * The LoadInternetAddresses class should take a filename as a string, uses BufferedReader
 * to read the file, split the lines into URLs and IPAddresses, and create the appropriate
 * objects. It should add those objects to a hash, and finally, after reading the whole file
 * it should return the instance of the hash.
 * 
 * If there is an error with the file format, you should throw a new FileFormatException error
 * with an appropriate message.
 *  
 * @author 
 *
 */
public class LoadInternetAddresses {
	HashI<URL, IPAddress> hash;

	public LoadInternetAddresses(){
		hash =  new Hash<URL, IPAddress>(100);
	}
	
	
	public HashI<URL, IPAddress> load_addresses(String filename) throws FileFormatException {
		//TODO You should write this method!
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(filename)); //check and read in filename
		} catch (Exception e ){
			System.out.println("Error in file");
			System.exit(-1);
		}
		try{
			String line;
			while ((line = in.readLine())!= null) { // check and split file
				String[] values = line.split("\t");
				URL urls = new URL(values[1]);
				IPAddress IPs = new IPAddress(values[0]);
				hash.add(urls, IPs);
			}
		} catch (Exception e){
			System.out.println("Error splitting file");
			e.printStackTrace();
		}
		return hash; //return the file
	}
}
