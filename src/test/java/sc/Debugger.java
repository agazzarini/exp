package sc;

import java.io.IOException;
import static java.util.Optional.of;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import static java.util.Arrays.asList;

/**
 * A "dummy" integration test for debugging the RequestHandler directly in Solr. 
 * 
 * @author agazzarini
 */
public class Debugger extends BaseIntegrationTest {
	/**
	 * Starts Solr and waits for the Enter key pressure.
	 * 
	 * @throws IOException in case of I/O failure.
	 */
	@Test
    public void start() throws Exception {
		String titleV1 = "This is the first version of the title";
		String titleV2 = "And this is the second version of the title, which contains some changes.";

		List<String> authors = asList("Massimiliano Branca", "Mario Rossi", "Giuseppe Verdi");

        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "1");
        document.setField("title", titleV2);
        document.setField("author", authors);

        getSolrClient().add(document);
        getSolrClient().commit();

        /*
        SolrQuery query = new SolrQuery("id:1");

        assertEquals(
        		titleV1,
				of(getSolrClient().query(query))
						.map(QueryResponse::getResults)
						.map(SolrDocumentList::iterator)
						.map(Iterator::next)
						.map(doc -> doc.getFieldValue("title"))
						.orElseThrow(IllegalStateException::new));

		// Replaces only the title
		SolrInputDocument withTitleReplacement = new SolrInputDocument();
		withTitleReplacement.setField("id", "1");

		Map<String, String> partialUpdatePayload = new HashMap<>();
		partialUpdatePayload.put("set", titleV2);

		withTitleReplacement.setField("title", partialUpdatePayload);

		getSolrClient().add(withTitleReplacement);
		getSolrClient().commit();

		assertEquals(
				titleV2,
				of(getSolrClient().query(query))
						.map(QueryResponse::getResults)
						.map(SolrDocumentList::iterator)
						.map(Iterator::next)
						.map(doc -> doc.getFieldValue("title"))
						.orElseThrow(IllegalStateException::new));
*/
		SolrQuery query = new SolrQuery("title:changse");
		QueryResponse response = getSolrClient().query(query);

		System.out.println(response);

		query = new SolrQuery("title:change");
		response = getSolrClient().query(query);

		System.out.println(response);

	}
}