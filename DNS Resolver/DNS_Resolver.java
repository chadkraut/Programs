package dns_resolver;

import java.util.Scanner;

import data_structures.HashI;
import exceptions.FileFormatException;

public class DNS_Resolver {

	public static void main(String[] args) {
		LoadInternetAddresses loader = new LoadInternetAddresses();

		HashI<URL, IPAddress> url2ip = null;
		try {
			// Change the two lines below to switch between
			// data sets.
			url2ip = loader.load_addresses("src/data/testIPs.txt");
		} catch (FileFormatException e) {
			System.err.println("There was a file format exception");
			e.printStackTrace();
		}

		System.out.print("Please enter a URL to find the IP address for. To exit, ");
		System.out.println("type quit and press return\nFor example:\nURL > edwards.sdsu.edu");
		URL request = new URL("edwards.sdsu.edu"); 
		if (url2ip.contains(request))
			System.out.println("The IP address for " + request + " is " + url2ip.getValue(request));
		else
			System.out.println("Error 404: The URL " + request + " is unknown");

		while (true){
			System.out.print("URL > ");
			@SuppressWarnings("resource")
			String ask = new Scanner(System.in).nextLine();
			if (ask.equals("quit"))
				System.exit(0);
			URL tofind = new URL(ask);
			if (url2ip.contains(tofind))
				System.out.println("The IP address for " + tofind + " is " + url2ip.getValue(tofind));
			else
				System.out.println("Error 404: The URL " + tofind + " is unknown");
		}
	}

}
