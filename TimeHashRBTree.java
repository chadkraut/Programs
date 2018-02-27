package timeDataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import data_structures.Hash;
import data_structures.LinkedList;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;

public class TimeHashRBTree {
	//create global variables for data structures
	static LinkedList<IPAddress> list = new LinkedList<>();
	static HashMap<IPAddress, URL> hashmap = new HashMap<>(325000);
	static Hash<IPAddress, URL> hash = new Hash<>(325000);
	static RedBlackTree<IPAddress, URL> RBTester = new RedBlackTree<>();
	static TreeMap<IPAddress, URL> treeMap = new TreeMap<>();


	public static void main(String[] args) {
		BufferedReader in = null;
		String line = "";
		long start, stop;
		String filename = "src/data/top-250k.ip";
		//load the linked list with the data
		try {
			in = new BufferedReader(new FileReader(filename));
			//while line is not null, split and add just the IP addresses
			while ((line = in.readLine()) != null) {
				String[] values = line.split("\t");
				list.add(new IPAddress(values[1]));
			}
			//catch an IO exception
		} catch (IOException e) {
			System.out.println("Error splitting file for Linked List");
			e.printStackTrace();
		}
		in = null;
		start = System.currentTimeMillis(); //time how long it takes to load the hash
		//load the hash with the data
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) { // check and split file
				String[] values = line.split("\t");
				hash.add(new IPAddress(values[1]), new URL(values[0])); // add it to the hash, (IP, URL)
			}
			//catch an IO exception
		} catch (IOException e) {
			System.out.println("Error splitting file for Hash");
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to load hash was " + (stop - start) + " milliseconds");

		in = null;
		start = stop = 0;
		start = System.currentTimeMillis(); // time how long it takes to search the hash
		for (IPAddress ip : list) {
			hash.getValue(ip);
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to search hash was " + (stop - start) + " milliseconds");
		System.out.println();

		hash = null; // clear the hash in memory

		in = null;
		start = stop = 0;
		start = System.currentTimeMillis(); //time how long it takes to load the RB tree
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) { // check and split file
				String[] values = line.split("\t");
				//creat IP and URL objects to be added to the tree that are from values
				IPAddress ip = new IPAddress(values[1]);
				URL url = new URL(values[0]);
				RBTester.add(ip, url);
			}
		} catch (IOException e) {
			System.out.println("Error splitting file for RB Tree");
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to load Red Black tree was " + (stop - start) + " milliseconds");

		start = stop = 0;
		start = System.currentTimeMillis(); // time how long it takes to search the tree
		for (IPAddress ip : list) {
			RBTester.getValue(ip);
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to search Red Black tree was " + (stop - start) + " milliseconds");
		System.out.println();
		RBTester = null; // clear the tree from memory


		start = System.currentTimeMillis(); //time how long it takes to load the java hashmap
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) { // check and split file
				String[] values = line.split("\t");
				hashmap.put(new IPAddress(values[1]), new URL(values[0])); // add url's and ip's to the hashmap
			}
		} catch (IOException e) {
			System.out.println("Error splitting file for hashmap");
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to load hashmap was " + (stop - start) + " milliseconds");

		start = stop = 0;
		start = System.currentTimeMillis(); //time how long it takes to search the java hashmap
		for (IPAddress ip : list) {
			hashmap.get(ip);
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to search hashmap was " + (stop - start) + " milliseconds");
		System.out.println();
		hashmap = null;


		start = stop = 0;
		start = System.currentTimeMillis(); // time how long it takes to load the java treemap
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) { // check and split file
				String[] values = line.split("\t");
				treeMap.put(new IPAddress(values[1]), new URL(values[0])); //add to the treemap
			}
		} catch (Exception e) {
			System.out.println("Error splitting file for treemap");
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to load treeMap was " + (stop - start) + " milliseconds");

		start = stop = 0;
		start = System.currentTimeMillis(); // begin timing how long it takes to search the treemap
		for (IPAddress ip : list) {
			treeMap.get(ip);
		}
		stop = System.currentTimeMillis();
		System.out.println("Total time to search treeMap was " + (stop - start) + " milliseconds");
		System.out.println();
		treeMap = null;




	}
}