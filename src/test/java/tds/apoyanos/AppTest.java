package tds.apoyanos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest 
    extends TestCase
{

    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        String a ="";
        StringBuilder sb = new StringBuilder();
        String almacenar = sb.toString();
        String[] strings = almacenar.split(";");
        for (String s : almacenar.split(";")) { a= s; }
        almacenar = "a;b;c;";
        strings = almacenar.split(";");
        almacenar = "a;";
        strings = almacenar.split(";");
        assertTrue( strings.length > 0 );
        assertTrue( a.equals(almacenar));
    }
}
