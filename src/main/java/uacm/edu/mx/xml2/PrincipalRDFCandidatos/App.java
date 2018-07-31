package uacm.edu.mx.xml2.PrincipalRDFCandidatos;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	testRDF();
    }
    static void testRDF(){
    	
    	FileManager.get().addLocatorClassLoader(App.class.getClassLoader());;
    	Model model = FileManager.get().loadModel("c:/Users/netoz/Documents/workspace-sts-3.9.4.RELEASE/PrincipalRDFCandidatos/src/main/java/uacm/edu/mx/xml2/rdf/rdf.rdf");
    	
    	//Consulta para extrer los datos del archivo RDF
    	String queryString =
    			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
    			"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
    					"SELECT * WHERE { " + 
    			" ?person foaf:name ?x ."+
    		//	" ?person foaf:knows ?person2 ."+
    		//	" ?person2 foaf:name ?y ."+
    		//	"FILTER( ?y = \"Ernesto\")" +
    			"}";
    	Query query = QueryFactory.create(queryString);
    	QueryExecution qexec = QueryExecutionFactory.create(query, model);
    	try {
    		ResultSet results = qexec.execSelect();
    		while (results.hasNext()) {
    			QuerySolution soln = results.nextSolution();
    			Literal name = soln.getLiteral("x");
    			System.out.print("\n" + name);
    		}
    		
    	}finally {
			qexec.close();
		}
    }
}
