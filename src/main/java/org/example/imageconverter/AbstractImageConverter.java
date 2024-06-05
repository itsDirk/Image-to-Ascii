package org.example.imageconverter;

public abstract class AbstractImageConverter implements IImageConverter {
    protected String path;

    public AbstractImageConverter(String path) {
        this.path = path;
    }

//    @Override
//    protected abstract char[][] convertImageToAscii() throws Exception;

}
