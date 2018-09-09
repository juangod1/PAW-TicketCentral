package ar.edu.itba.paw2018b;

import static org.junit.Assert.assertTrue;

import ar.edu.itba.paw2018b.interfaces.dao.TransactionDao;
import ar.edu.itba.paw2018b.models.Screening;
import ar.edu.itba.paw2018b.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Mock
    TransactionDao td;

    @Mock
    Screening sc;

    @Before
    public void setUp() throws Exception{
        //Mockito.when(sc.getFormat()).thenReturn(new String[] = "mocking");
        //Mockito.when(td.getOcuppiedSeatsByScreening(sc)).thenReturn(occTr);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    //ForTransactionServiceImpl
    @Test
    public void TestGetSeatsByScreening(){
        //Le doy una funcion
        //Me devuelve una lista de asientos que incluye si estan disponibles o no.
    }
}
