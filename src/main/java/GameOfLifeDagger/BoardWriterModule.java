package GameOfLifeDagger;

import com.company.BoardGifWriter;
import com.company.BoardPrintWriter;
import com.company.BoardWriter;
import com.company.StartingParameterValues;
import dagger.Module;
import dagger.Provides;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generates an instance of a BoardWriter, which may be BoardWriterException if an IOException is thrown during the BoardWriter's construction.
 */
@Module
public class BoardWriterModule {
    @Provides
    BoardWriter provideWriter(StartingParameterValues params){
        try {
            return new BoardGifWriter(
                    new com.company.GifSequenceWriter(
                            new FileImageOutputStream(
                                    new File(params.outputFilename)
                            ),
                            BufferedImage.TYPE_USHORT_555_RGB, 100, false
                    )
            );
        }catch (IOException ioe){
            return new BoardWriterException(ioe);
        }
    }
}
