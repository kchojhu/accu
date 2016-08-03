package service;

import java.util.Map.Entry;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.junit.Test;

public class AccumuloServiceIntegrationTest {

	@Test
	public void readData() {
		// ClientOpts clientOpts = new ClientOpts();
		// clientOpts.setPrincipal("root");
		// ClientOpts.Password password = new ClientOpts.Password("password");
		// clientOpts.setPassword(password);
		// clientOpts.instance = "instance1";
		// clientOpts.zookeepers = "fn1.co:2181";
		//
		// ScannerOpts scanOpts = new ScannerOpts();
		// BatchWriterOpts bwOpts = new BatchWriterOpts();
		try {
			Instance instance = new ZooKeeperInstance("accu", "localhost:2181");
			Connector connector = instance.getConnector("kwcho", "password".getBytes());
			Authorizations authorizations = new Authorizations("public");
			Scanner scan = connector.createScanner("mytable", authorizations);

			for (Entry<Key, Value> entry : scan) {
				String row = entry.getKey().getRow().toString();
				Value value = entry.getValue();

				System.out.println("row:" + row + " value:" + value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
