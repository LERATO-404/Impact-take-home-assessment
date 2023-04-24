import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class NumberRangeSummarizerTest {

    private NumberRangeSummarizer underTest;

    @Before
    public void setUp(){
        underTest = new NumberRangeSummarizerImp();
    }

    @Test
    public void testcollect_IsEmptyInput() {
        String collectInput = "";

        List<Integer> expectedOutput = Arrays.asList();


        Collection<Integer> collectResult = underTest.collect(collectInput);
        assertEquals(expectedOutput, collectResult);
        
    }

    @Test
    //@Ignore
    public void testCollect_IsNotEmptyInput() {
        String input = "1,2,3";

        List<Integer> expectedOutput = Arrays.asList(1,2,3);

        Collection<Integer> result = underTest.collect(input);
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testcollect_Pass() {
        String input = "1,3,4,6,7,8,10";

        Collection<Integer> expectedOutput = Arrays.asList(1,3,4,6,7,8,10);

        Collection<Integer> result = underTest.collect(input);
        Assert.assertEquals(expectedOutput, result);
    }

    @Test
    public void testcollect_Fail() {
        String input = "1,3,4,6,7,8,10";

        Collection<Integer> expectedOutput = Arrays.asList(1,2,3,4,6,7,8,10);

        Collection<Integer> result = underTest.collect(input);
        Assert.assertNotEquals(expectedOutput, result);
    }

    @Test
    public void testcollect_ContainsNonNumeric() {

        String input = "1,3,4,a,7,8,10d";

        List<Integer> expectedOutput = Arrays.asList(1,3,4,7,8,10);


        Collection<Integer> result = underTest.collect(input);
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void testcollect_ContainsDuplicates() {
        String input = "1,3,4,7,7,8,10";

        List<Integer> expectedOutput = Arrays.asList(1,3,4,7,7,8,10);


        Collection<Integer> result = underTest.collect(input);
        Assert.assertEquals(expectedOutput, result);
    }

    @Test
    public void testsummarizeCollection_Pass() {
        Collection<Integer> input = Arrays.asList(1,3,4,6,7,8, 10);

        String expectedOutput = "1, 3-4, 6-8, 10";

        String result = underTest.summarizeCollection(input);
        Assert.assertEquals(expectedOutput, result);
    }

    @Test
    public void testsummarizeCollection_Fail() {
        Collection<Integer> input = Arrays.asList(1,3,4,6,7,8, 10);

        String expectedOutput = "1, 3-4, 6, 10";

        String result = underTest.summarizeCollection(input);
        Assert.assertNotEquals(expectedOutput, result);
    }

    @Test
    public void testsummarizeCollection_ContainsEmptyCollection() {
        Collection<Integer> input = Collections.EMPTY_LIST;

        String expectedOutput = "";

        String result = underTest.summarizeCollection(input);
        Assert.assertEquals(expectedOutput, result);
    }


    @Test
    public void testsummarizeCollection_ContainsDuplicates() {
        Collection<Integer> input = Arrays.asList(1, 3, 4, 6, 7, 7, 8, 10);

        String expectedOutput = "1, 3-4, 6-8, 10";

        String result = underTest.summarizeCollection(input);
        Assert.assertEquals(expectedOutput, result);
    }


    @Test
    public void testsummarizeCollection_Unsorted() {
        Collection<Integer> input = Arrays.asList(1, 3, 7, 9, 4, 6, 7, 8, 11);

        String expectedOutput = "1, 3-4, 6-9, 11";

        String result = underTest.summarizeCollection(input);
        Assert.assertEquals(expectedOutput, result);
    }



}