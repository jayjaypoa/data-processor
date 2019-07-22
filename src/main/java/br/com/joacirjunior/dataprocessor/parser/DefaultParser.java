package br.com.joacirjunior.dataprocessor.parser;

public abstract class DefaultParser<E> {

    public final static int ID = 0;
    public abstract E parse(String[] data);

}
