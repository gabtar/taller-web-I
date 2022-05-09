package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FailTest {
    @Test
    public void testFailIntegracionCountinua() {
        assertThat(true).isEqualTo(false);
    }

}