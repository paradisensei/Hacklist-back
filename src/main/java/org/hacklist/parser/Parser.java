package org.hacklist.parser;

import org.hacklist.model.Hack;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Aidar Shaifutdinov.
 */
public interface Parser {

    Collection<Hack> parse() throws IOException;

}
