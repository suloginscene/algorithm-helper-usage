package cf.scenecho.algorithm.encode;

import com.github.suloginscene.algorithmhelper.core.encode.Encoder;
import com.github.suloginscene.algorithmhelper.core.encode.EncoderProfiler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HuffmanEncoderTest {

    Encoder encoder;


    @BeforeEach
    void setup() {
        encoder = new EncoderProfiler(new HuffmanEncoder());
    }


    @Test
    void test() {
        String original = "Hello, world! This is Huffman coding for lossless compression.\n" +
                "한국어도 잘 부호화됩니다. 바이너리 스트링을 비트열로 변환하면 압축이 됩니다.";

        String encoded = encoder.encode(original);
        assertNotEquals(original, encoded);

        String decoded = encoder.decode(encoded);
        assertEquals(original, decoded);
    }

}